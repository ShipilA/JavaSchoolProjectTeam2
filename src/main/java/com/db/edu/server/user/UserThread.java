package com.db.edu.server.user;

import com.db.edu.server.Message;
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
                processMessages(new Message(user.getMessage()));
            }

        } catch (ServerException ex) {
            //TODO add logger
        } finally {
            room.removeUserFromList(user);
//            user.close();
        }
    }

    public void processMessages(Message msg) throws ServerException {
        if (msg.isKey("/snd")){
            room.saveMessage(msg);
            room.sendMessageToAllOtherUsers(user, msg.toString());
        }
        if (msg.isKey("/hist")){
            room.sendMessageHistoryToUser(user);
        }
        if (msg.isKey("/chid")){
            room.sendMessageHistoryToUser(user);
        }

    }
}