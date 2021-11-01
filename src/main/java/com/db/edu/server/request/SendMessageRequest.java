package com.db.edu.server.request;

public class SendMessageRequest implements Request{
    @Override
    public boolean isResponseOfThisType() {
        return false;
    }

    @Override
    public void handleResponse() {

    }
}
