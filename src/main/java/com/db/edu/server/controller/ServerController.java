package com.db.edu.server.controller;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.request.Request;
import com.db.edu.server.request.RequestFactory;

public class ServerController {

    public void getRequest(String message) throws ServerException {
        RequestFactory requestFactory = new RequestFactory();
        Request request = requestFactory.getRequest(message);
    }


    public String sendResponse() {
        return null;
    }
}
