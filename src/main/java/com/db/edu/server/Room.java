package com.db.edu.server;

import com.db.edu.server.database.RoomMessagesDB;
import com.db.edu.server.exception.ServerException;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Room {

    private String name = "";

    private final ArrayList<User> users = new ArrayList<>();

    public RoomMessagesDB roomMessages;

    Room (String name){
        this.name = name;
        roomMessages = new RoomMessagesDB(new File(name+".csv"));
    }

    public synchronized void addUserToList(User user) {
        users.add(user);
    }

    public synchronized void removeUserFromList(User user) {
        users.add(user);
    }

    public synchronized void sendMessageToAllOtherUsers(Message msg) throws ServerException {
        for (User receiver : users) {
            PrintWriter out = new PrintWriter(receiver.getOutputStream());
            out.println(msg.toString());
            out.flush();
        }
    }

    public synchronized void sendMessageToAllOtherUsers(User user) throws ServerException {
        for (User receiver : users) {
            if (!user.equals(receiver)) {
                PrintWriter out = new PrintWriter(user.getOutputStream());
                out.println(user.getMessage());
                out.flush();
            }
        }
    }
}