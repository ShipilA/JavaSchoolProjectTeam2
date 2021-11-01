package com.db.edu.server.request;

import com.db.edu.server.exception.ServerException;

public class RequestFactory {
    public Request getRequest(String message) throws ServerException {
        Request request = new ShowHistoryRequest();
        if (request.isResponseOfThisType(message)) {
            return request;
        } else {
            request = new SendMessageRequest();
            if (request.isResponseOfThisType(message)) {
                return request;
            }
        }
        throw new ServerException("Wrong message format. Unable to parse the request.");
    }
}
