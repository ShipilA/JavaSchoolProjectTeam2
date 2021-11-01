package com.db.edu.server.request;

import com.db.edu.server.exception.ServerException;

public class ShowHistoryRequest implements Request {
    private static final String HISTORY_PREFIX = "/hist";

    private final String message;

    public ShowHistoryRequest(String message) {
        this.message = message;
    }

    @Override
    public boolean isRequestOfThisType() {
        return message.equals(HISTORY_PREFIX);
    }

    //TODO add realisation
    @Override
    public void handleResponse() throws ServerException {
        if (!isRequestOfThisType()) {
            throw new ServerException("Wrong type of response");
        }
    }
}
