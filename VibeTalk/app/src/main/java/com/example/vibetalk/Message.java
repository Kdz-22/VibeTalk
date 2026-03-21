package com.example.vibetalk;

public class Message{

	private String text;
	private String time;
	private boolean isSent = true; // true - enviada por mim; false - recebida

	public Message(String text, String time, boolean isSent){
		this.text = text;
		this.time = time;
		this.isSent = isSent;
	}

	public String getText(){ return text; }
	public String getTime(){ return time; }
	public boolean getSent(){ return isSent; }

}