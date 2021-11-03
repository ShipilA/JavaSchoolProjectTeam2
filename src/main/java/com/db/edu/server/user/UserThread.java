package com.db.edu.server.user;

import com.db.edu.server.MessageFacade;
import com.db.edu.server.MessageFacadeException;
import com.db.edu.server.exception.ServerException;
import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;
import com.db.edu.server.rooms.Room;

import java.util.Objects;

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
            MessageFacade messageFacade = new MessageFacade();
            while (!Thread.interrupted()) {
                try {
                    processMessages(messageFacade.processIncomingMessage(user.getMessage(), user.getName()));
                } catch (MessageFacadeException ex) {
                    if (Objects.equals(ex.getMessage(), "User message length > 150")) {
                        System.out.println(ex.getMessage());
                        try {
                            room.sendMessageToUser(user, ex.getMessage());
                        } catch (ServerException e) {
                            e.printStackTrace();
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }

        } catch (ServerException ex) {
            System.out.println(ex.getMessage());

            //TODO add logger
        } finally {
            room.removeUserFromList(user);
//            user.close();
        }
    }

    public void processMessages(Message msg) throws ServerException {
        if (msg instanceof SendMessage) {
            room.saveMessage(msg);
            room.sendMessageToAllOtherUsers(user, msg.toString());
        }
        if (msg instanceof HistoryMessage) {
            room.sendMessageHistoryToUser(user);
        }
        if (msg instanceof SetUserNameMessage) {
            user.setName(msg.getData());
        }

    }


}