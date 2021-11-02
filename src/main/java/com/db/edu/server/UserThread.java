package com.db.edu.server;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.rooms.Room;

public class UserThread implements Runnable {

    private final User user;
    private final Room room;

    public UserThread(User user, Room room) {
        this.user = user;
        this.room = room;
    }

    @Override
    public void run() {
        try {
            room.addUserToList(user);
            while (!Thread.interrupted()) {
                Message message = new Message(user.getMessage());
                room.saveMessage(message);
                room.sendMessageToAllOtherUsers(user, message.toString());
            }

        } catch (ServerException ex) {
            //TODO add logger
        } finally {
            room.removeUserFromList(user);
//            user.close();
        }
    }
}