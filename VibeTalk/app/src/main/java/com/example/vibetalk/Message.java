package com.example.vibetalk;

/**
 * Classe de modelo para representar uma mensagem no chat.
 * Armazena o texto, o horário e quem enviou.
 */
public class Message {

    private String text;    // Conteúdo da mensagem
    private String time;    // Horário do envio (ex: 10:30)
    private boolean isSent; // Define se a mensagem foi enviada por "mim" (true) ou recebida (false)

    public Message(String text, String time, boolean isSent) {
        this.text   = text;
        this.time   = time;
        this.isSent = isSent;
    }

    public String getText()  { return text; }
    public String getTime()  { return time; }
    
    // Esse método é crucial para o Adapter decidir qual layout usar (Flexbox à esquerda ou direita)
    public boolean isSent()  { return isSent; }

}
