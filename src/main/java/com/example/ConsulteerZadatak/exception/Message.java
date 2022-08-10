package com.example.ConsulteerZadatak.exception;

public class Message {
    private String error;

    public Message(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
