package com.db.edu.server.rooms;

import com.db.edu.server.Message;
import com.db.edu.server.User;
import com.db.edu.server.database.RoomMessagesDB;
import com.db.edu.server.exception.ServerException;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Room {

    private final String name;
    private final ArrayList<User> users = new ArrayList<>();
    private RoomMessagesDB roomMessages;

    public Room(String name) {
        this.name = name;
        roomMessages = new RoomMessagesDB(name);
    }

    public synchronized void saveMessage(Message msg){
        roomMessages.saveMessage(msg);
    }

    public synchronized void addUserToList(User user) {
        users.add(user);
    }

    public synchronized void removeUserFromList(User user) {
        users.add(user);
    }

    public synchronized void sendMessageToAllOtherUsers(User user) throws ServerException {
        sendMessageToAllOtherUsers(user, user.getMessage());
    }

    public synchronized void sendMessageToAllOtherUsers(User user, String message) throws ServerException {
        for (User receiver : users) {
            if (!user.equals(receiver)) {
                PrintWriter out = new PrintWriter(user.getOutputStream());
                out.println(message);
                out.flush();
            }
        }
    }
}