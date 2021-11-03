package com.db.edu.server;

import com.db.edu.server.message.*;
import com.db.edu.server.rooms.RoomContainer;

public class MessageFacade {
    private static final int MESSAGE_MAX_LENGTH = 150;
    private RoomContainer roomContainer;

    public MessageFacade(RoomContainer roomContainer) {
        this.roomContainer = roomContainer;
    }

    public MessageFacade () {
    }

    public Message processIncomingMessage(String inMessage, String fromUserName) throws MessageFacadeException {
        String[] items = inMessage.split(" ", 2);
        if (items.length > 1) {
            if (items[0].contains("/snd")) {
                if (items[1].length() > MESSAGE_MAX_LENGTH) {
                    throw new MessageFacadeException("User message length > 150");
                }
                return new SendMessage(fromUserName, items[1]);
            }
            if (items[0].contains("/chid")) {
                if (roomContainer.isNameTaken(items[1])) {
                    throw new MessageFacadeException("User name is already taken");
                }
                return new SetUserNameMessage(fromUserName, items[1]);
            }
            if (items[0].contains("/chroom")) {
                return new ChRoomMessage(fromUserName, items[1]);
            }
        } else if (items.length > 0) {
            if (items[0].contains("/hist")) {
                return new HistoryMessage();
            }
        }

        throw new MessageFacadeException("User wrong command");
    }

}
