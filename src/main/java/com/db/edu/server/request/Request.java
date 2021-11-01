package com.db.edu.server.request;

import com.db.edu.server.exception.ServerException;

public interface Request {
    boolean isRequestOfThisType();
    void handleResponse() throws ServerException;
}
