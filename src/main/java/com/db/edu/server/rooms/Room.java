package com.db.edu.server.rooms;

import com.db.edu.server.message.Message;
import com.db.edu.server.user.User;
import com.db.edu.server.database.RoomMessagesDB;
import com.db.edu.server.exception.ServerException;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Room {

    private final String name;
    private final List<User> users = new LinkedList<>();
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

    public synchronized void saveMessage(Message msg){
        roomMessages.saveMessage(msg);
    }

    public synchronized String messageHistory(){
        return roomMessages.getAllMessages();
    }

    public synchronized void addUserToList(User user) {
        users.add(user);
    }

    public synchronized void removeUserFromList(User user) {
        users.add(user);
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

}