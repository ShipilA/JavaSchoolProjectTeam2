package com.db.edu.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.db.edu.server.exception.ServerException;
import com.db.edu.server.request.Request;
import com.db.edu.server.request.RequestFactory;
import com.db.edu.server.request.SendMessageRequest;
import com.db.edu.server.request.ShowHistoryRequest;

public class ServerImpl implements Server{
    final Logger logger = LoggerFactory.getLogger(ServerImpl.class);
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
