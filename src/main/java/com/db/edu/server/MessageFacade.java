package com.db.edu.server;

import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;

public class MessageFacade {
    private static final int MESSAGE_MAX_LENGTH = 150;
    private UserThreadsController controller;

    public MessageFacade(UserThreadsController controller) {
        this.controller = controller;
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
                if (controller.isNameTaken(items[1])) {
                    throw new MessageFacadeException("User name is already taken");
                }
                return new SetUserNameMessage(fromUserName, items[1]);
            }
        } else if (items.length > 0 && items[0].contains("/hist")) {
            return new HistoryMessage();
        }

        throw new MessageFacadeException("User wrong command");
    }

}
