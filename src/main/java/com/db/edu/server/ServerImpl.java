package com.db.edu.server;

import com.db.edu.server.controller.ServerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.db.edu.server.exception.ServerException;

public class ServerImpl implements Server {
    final Logger logger = LoggerFactory.getLogger(ServerImpl.class);
    ServerController serverController = new ServerController();

    @Override
    public void getRequest(String message) {
        try {
            serverController.getRequest(message);
        } catch (ServerException e) {
            //TODO refactor catch
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String sendResponse() {
        return serverController.sendResponse();
    }
}
