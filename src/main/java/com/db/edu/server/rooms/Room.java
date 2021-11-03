package com.db.edu.server.rooms;

import com.db.edu.server.database.RoomMessagesDB;
import com.db.edu.server.exception.ServerException;
import com.db.edu.server.message.Message;
import com.db.edu.server.user.User;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Room {

    private final String name;
    private final Set<User> users = new HashSet<>();
    private final RoomMessagesDB roomMessages;

    public Room(String name) {
        this.name = name;
        roomMessages = new RoomMessagesDB(name);
    }

    public Room() {
        this("Default room");
    }

    public String getName() {
        return name;
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

    public synchronized void sendMessageToAllOtherUsers(User user, String message) throws ServerException {
        for (User receiver : users) {
            if (!user.equals(receiver)) {
                PrintWriter out = new PrintWriter(receiver.getOutputStream());
                out.println(message);
                out.flush();
            }
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

    public synchronized boolean isNameTaken(String name) {
        return users.stream().anyMatch(user -> user.getName().equals(name));
    }

}