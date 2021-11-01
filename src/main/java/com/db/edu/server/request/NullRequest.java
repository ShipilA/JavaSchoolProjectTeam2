package com.db.edu.server.request;

public class NullRequest implements Request{
    @Override
    public boolean isResponseOfThisType() {
        return false;
    }

    @Override
    public void handleResponse() {

    }
}
