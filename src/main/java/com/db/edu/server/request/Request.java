package com.db.edu.server.request;

public interface Request {
    boolean isResponseOfThisType();
    void handleResponse();
}
