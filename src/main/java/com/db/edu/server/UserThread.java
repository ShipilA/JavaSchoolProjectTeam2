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
            while (!Thread.interrupted()) {
                Message msg = new Message(user.getMessage());

                room.saveMessage(msg);

                room.sendMessageToAllOtherUsers(msg);
            }

        } catch (ServerException ex) {
            //TODO add logger
        } finally {
            room.removeUserFromList(user);
//            user.close();
        }
    }
}