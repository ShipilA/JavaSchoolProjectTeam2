package com.db.edu.server.request;

import com.db.edu.server.exception.ServerException;

public interface Request {
    boolean isResponseOfThisType();
    void handleResponse() throws ServerException;
}
