package com.db.edu.server.request;

import com.db.edu.server.exception.ServerException;

public class RequestFactory {
    public Request getRequest(String message) throws ServerException {
        Request request = new ShowHistoryRequest(message);
        if (request.isRequestOfThisType()) {
            return request;
        } else {
            request = new SendMessageRequest(message);
            if (request.isRequestOfThisType()) {
                return request;
            }
        }
        throw new ServerException("Wrong message format. Unable to parse the request.");
    }
}
