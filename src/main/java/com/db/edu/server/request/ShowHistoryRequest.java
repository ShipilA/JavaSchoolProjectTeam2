package com.db.edu.server.request;

public class ShowHistoryRequest implements Request{
    @Override
    public boolean isResponseOfThisType() {
        return false;
    }

    @Override
    public void handleResponse() {

    }
}
