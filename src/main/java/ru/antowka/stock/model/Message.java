package ru.antowka.stock.model;

/**
 * Created by Anton Nik on 29.10.15.
 */
public class Message {

    private int messageId;
    private String message;

    public Message(int messageId, String message) {
        this.messageId = messageId;
        this.message = message;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
