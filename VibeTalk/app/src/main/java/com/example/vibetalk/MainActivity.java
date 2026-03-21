package com.example.vibetalk;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private List<Message> messages;
    private MessageAdapter adapter;
    private RecyclerView recyclerView;
    private EditText editText;
    private ImageButton btnSend;

    // Alterna entre "eu" e "Kevin" para simular uma conversa
    private boolean myTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        editText     = findViewById(R.id.editTextMessage);
        btnSend      = findViewById(R.id.btnSend);

        messages = new ArrayList<>();
        adapter  = new MessageAdapter(messages);

        // LinearLayoutManager simples — o Flexbox está nos itens!
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true); // novas mensagens aparecem embaixo
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Mensagens iniciais de exemplo
        addMessage("Oi! Tudo bem?", false);
        addMessage("Tudo sim! E você?", true);
        addMessage("Aprendendo Flexbox", false);

        // Botão Enviar
        btnSend.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            if (!text.isEmpty()) {
                addMessage(text, myTurn);
                myTurn = !myTurn; // alterna remetente para testar os dois layouts
                editText.getText().clear();
            }
        });
    }

    private void addMessage(String text, boolean isSent) {
        String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        messages.add(new Message(text, time, isSent));
        adapter.notifyItemInserted(messages.size() - 1);
        recyclerView.scrollToPosition(messages.size() - 1);
    }
}
