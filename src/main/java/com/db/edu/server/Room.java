package com.db.edu.server;

import com.db.edu.server.exception.ServerException;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Room {

    private ArrayList<User> users = new ArrayList<User>();

    public synchronized void addUserToList(User user) {
        users.add(user);
    }

    public synchronized void removeUserFromList(User user) {
        users.add(user);
    }

    public void sendMessageToAllOtherUsers(User user) throws ServerException {
        for (User receiver : users) {
            if (!user.equals(receiver)) {
                PrintWriter out = new PrintWriter(user.getOutputStream());
                out.println(user.getMessage());
                out.flush();
            }
        }
    }
}