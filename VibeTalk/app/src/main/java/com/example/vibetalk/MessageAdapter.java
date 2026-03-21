package com.example.vibetalk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter para o RecyclerView que gerencia a exibição das mensagens.
 * Ele decide se carrega o layout de "enviado" (direita) ou "recebido" (esquerda).
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Constantes para identificar o tipo de layout
    private static final int TYPE_SENT = 1;
    private static final int TYPE_RECEIVED = 2;

    private final List<Message> messages;

    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * ViewHolder para mensagens enviadas.
     * Mapeia os componentes do layout item_message_sent.xml.
     */
    static class SentViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        TextView tvTime;

        SentViewHolder(View v) {
            super(v);
            tvMessage = v.findViewById(R.id.tvMessage);
            tvTime = v.findViewById(R.id.tvTime);
        }
    }

    /**
     * ViewHolder para mensagens recebidas.
     * Mapeia os componentes do layout item_message_received.xml.
     */
    static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTime;

        ReceivedViewHolder(View view) {
            super(view);
            tvMessage = view.findViewById(R.id.tvMessage);
            tvTime    = view.findViewById(R.id.tvTime);
        }
    }

    /**
     * Define qual o tipo de view para a posição atual da lista.
     * Aqui usamos a lógica do objeto Message para saber se foi enviada ou recebida.
     */
    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isSent() ? TYPE_SENT : TYPE_RECEIVED;
    }

    /**
     * Infla (cria) o layout XML correspondente ao tipo da mensagem.
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_SENT) {
            // Se for enviada, usa o layout com Flexbox alinhado à DIREITA (flex-end)
            View view = inflater.inflate(R.layout.item_message_sent, parent, false);
            return new SentViewHolder(view);
        } else {
            // Se for recebida, usa o layout com Flexbox alinhado à ESQUERDA (flex-start)
            View view = inflater.inflate(R.layout.item_message_received, parent, false);
            return new ReceivedViewHolder(view);
        }
    }

    /**
     * Preenche os dados da mensagem nos componentes do layout.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message msg = messages.get(position);

        if (holder instanceof SentViewHolder) {
            SentViewHolder h = (SentViewHolder) holder;
            h.tvMessage.setText(msg.getText());
            h.tvTime.setText(msg.getTime());

        } else if (holder instanceof ReceivedViewHolder) {
            ReceivedViewHolder h = (ReceivedViewHolder) holder;
            h.tvMessage.setText(msg.getText());
            h.tvTime.setText(msg.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
