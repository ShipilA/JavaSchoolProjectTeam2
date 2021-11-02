package com.db.edu.server;

import com.db.edu.server.exception.ServerException;

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
            room.sendMessageToAllOtherUsers(user);
            room.removeUserFromList(user);
            user.close();
        } catch (ServerException ex) {
            //TODO add logger
        }
    }
}