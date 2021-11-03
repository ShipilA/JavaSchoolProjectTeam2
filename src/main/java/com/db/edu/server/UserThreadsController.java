package com.db.edu.server;

import com.db.edu.server.database.RoomMessagesDB;
import com.db.edu.server.exception.ServerException;
import com.db.edu.server.message.Message;
import com.db.edu.server.user.User;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class UserThreadsController {

    private final Set<User> users = new HashSet<>();
    private final RoomMessagesDB roomMessages;

    public UserThreadsController() {
        roomMessages = new RoomMessagesDB("Default room");
    }

    public synchronized void saveMessage(Message msg) {
        roomMessages.saveMessage(msg);
    }

    public synchronized String messageHistory() {
        return roomMessages.getAllMessages();
    }

    public synchronized void addUserToList(User user) {
        users.add(user);
    }

    public synchronized void removeUserFromList(User user) {
        users.remove(user);
    }

    //todo create read-only
    public synchronized void sendMessageToAllUsers(String message) throws ServerException {
        for (User receiver : users) {
            PrintWriter out = new PrintWriter(receiver.getOutputStream());
            out.println(message);
            out.flush();
        }
    }

    public synchronized void sendMessageHistoryToUser(User user) throws ServerException {
        PrintWriter out = new PrintWriter(user.getOutputStream());
        out.println(messageHistory());
        out.flush();
    }

    public synchronized void sendMessageToUser(User user, String msg) throws ServerException {
        PrintWriter out = new PrintWriter(user.getOutputStream());
        out.println(msg);
        out.flush();
    }

    public boolean isNameTaken(String name) {
        for (User user : users) {
            if (name.equals(user.getName())) {
                return true;
            }
        }
        return false;
    }
}