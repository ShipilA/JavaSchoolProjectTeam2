package com.db.edu.server.controller;

import com.db.edu.server.Message;
import com.db.edu.server.database.DataBase;
import com.db.edu.server.database.RoomMessagesDB;
import com.db.edu.server.exception.ServerException;
import com.db.edu.server.request.Request;
import com.db.edu.server.request.RequestFactory;

import java.io.File;

public class ServerController {
    Request request;
    DataBase roomMessagesDataBase;

    public ServerController() {
        this.roomMessagesDataBase = new RoomMessagesDB("data.csv");
    }

    public void getRequest(String message) throws ServerException {


    }

    public String sendClientsNewMessage(Message msg) {
        return msg.toString();
    }

    public String sendResponse() {
        return "";
    }
}
