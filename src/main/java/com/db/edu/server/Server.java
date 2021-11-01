package com.db.edu.server;

public interface Server {

    void getRequest(String message);

    String sendResponse();
}
