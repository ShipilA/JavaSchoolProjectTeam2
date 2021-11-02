package com.db.edu.server.request;

import com.db.edu.server.exception.ServerException;

public class SendMessageRequest implements Request {
    private static final String SEND_PREFIX = "/snd ";
    private static final int MAX_LENGTH = 150;

    private final String message;

    public SendMessageRequest(String message) {
        this.message = message;
    }

    @Override
    public boolean isRequestOfThisType() {
        return message.startsWith(SEND_PREFIX);
    }

    //TODO add realisation
    @Override
    public void handleResponse() throws ServerException {
        if (!isRequestOfThisType()) {
            throw new ServerException("Wrong type of response");
        }
        if (message.substring(SEND_PREFIX.length()).length() > MAX_LENGTH) {
            throw new ServerException("Too long message. Maximum length is " + MAX_LENGTH + " symbols");
        }
    }
}
