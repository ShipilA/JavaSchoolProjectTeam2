package com.db.edu.server.user;

import com.db.edu.server.MessageFacade;
import com.db.edu.server.MessageFacadeException;
import com.db.edu.server.exception.ServerException;
import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;
import com.db.edu.server.rooms.Room;
import com.db.edu.server.rooms.RoomContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class UserThread implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(UserThread.class);
    private final User user;
    private final Room room;
    private RoomContainer roomContainer;

    public UserThread(User user, Room room) {
        this.user = user;
        this.room = room;
    }

    public UserThread(User user, Room room, RoomContainer roomContainer) {
        this.user = user;
        this.room = room;
        this.roomContainer = roomContainer;
    }

    @Override
    public void run() {
        try {
            room.addUserToList(user);
            MessageFacade messageFacade = new MessageFacade(roomContainer);
            while (!Thread.interrupted()) {
                try {
                    processMessages(messageFacade.processIncomingMessage(user.getMessage(), user.getName()));
                } catch (MessageFacadeException ex) {
                    if (Objects.equals(ex.getMessage(), "User message length > 150")
                            || Objects.equals(ex.getMessage(), "User name is already taken")) {
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
            log.error("Error in UserThread: ", ex);
        } finally {
            room.removeUserFromList(user);
            user.close();
        }
    }

    public void processMessages(Message msg) throws ServerException {
        if (msg instanceof SendMessage) {
            room.saveMessage(msg);
            room.sendMessageToAllUsers(msg.toString());
        }
        if (msg instanceof HistoryMessage) {
            room.sendMessageHistoryToUser(user);
        }
        if (msg instanceof SetUserNameMessage) {
            user.setName(msg.getData());
        }

    }


}