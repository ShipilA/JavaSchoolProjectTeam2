package com.db.edu.server.request;

public class ShowHistoryRequest implements Request{
    @Override
    public boolean isResponseOfThisType(String message) {
        return message.equals("/hist");
    }

    //TODO add realisation
    @Override
    public void handleResponse() {
    }
}
