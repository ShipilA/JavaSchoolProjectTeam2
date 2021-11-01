package com.db.edu.server;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.request.Request;
import com.db.edu.server.request.RequestFactory;
import com.db.edu.server.request.SendMessageRequest;
import com.db.edu.server.request.ShowHistoryRequest;

public class ServerImpl implements Server{
    @Override
    public void getRequest(String message) {
        RequestFactory requestFactory = new RequestFactory();
        try {
            requestFactory.getRequest(message);
        } catch (ServerException e) {
            //TODO refactor catch
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String sendResponse() {
        return null;
    }
}
