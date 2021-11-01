package com.db.edu.server.request;

public interface Request {
    boolean isResponseOfThisType(String message);
    void handleResponse();
}
