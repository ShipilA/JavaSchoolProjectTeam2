package com.db.edu.server;

import com.db.edu.server.message.*;

import java.util.InputMismatchException;

class WrongInputException extends InputMismatchException {

}

public class MessageFacade {
    private static final int MSGMAXLEN = 150;

    public Message processIncomingMessage(String inMessage, String userName){
        String[] items = inMessage.split(" ", 2);
        if (items.length > 1) {
            if (items[0].contains("/snd")) {
                if (items[1].length() > MSGMAXLEN) {
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

        throw new InputMismatchException("User wrong command");
    }

}
