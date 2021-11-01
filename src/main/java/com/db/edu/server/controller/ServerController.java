package com.db.edu.server.controller;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.request.Request;
import com.db.edu.server.request.RequestFactory;

public class ServerController {
    Listener listener;
    Request request;

    public ServerController() {
        this.listener = new Listener();
        Thread thread = new Thread(listener);
        thread.start();
    }

    public void getRequest(String message) throws ServerException {
        RequestFactory requestFactory = new RequestFactory();
        request = requestFactory.getRequest(message);
        request.handleResponse();
    }


    public String sendResponse() {
        return "";
    }
}
