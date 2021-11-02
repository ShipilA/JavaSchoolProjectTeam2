package com.db.edu.server;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.message.*;

public class MessageFacade {
    private static final int MESSAGE_MAX_LENGTH = 150;

    public Message processIncomingMessage(String inMessage, String userName) throws ServerException {
        String[] items = inMessage.split(" ", 2);
        if (items.length > 1) {
            if (items[0].contains("/snd")) {
                if (items[1].length() > MESSAGE_MAX_LENGTH) {
                    //TODO handle this case
                }
                return new SendMessage(userName, items[1]);
            }
            if (items[0].contains("/chid")) {
                return new SetUserNameMessage();
            }
            if (items[0].contains("/chroom")) {
                return new ChRoomMessage(userName, items[1]);
            }
        } else if (items.length > 0) {
            if (items[0].contains("/history")) {
                return new HistoryMessage();
            }
        }

        throw new ServerException("User wrong command");
    }

}
