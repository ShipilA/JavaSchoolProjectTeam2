package com.db.edu.server.request;

public class SendMessageRequest implements Request{
    @Override
    public boolean isResponseOfThisType(String message) {
        return message.startsWith("/snd ");
    }

    //TODO add realisation
    @Override
    public void handleResponse() {

    }
}
