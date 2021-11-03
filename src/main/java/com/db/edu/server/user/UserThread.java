package com.db.edu.server.user;

import com.db.edu.server.MessageFacade;
import com.db.edu.server.MessageFacadeException;
import com.db.edu.server.exception.ServerException;
import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;
import com.db.edu.server.UserThreadsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class UserThread implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(UserThread.class);
    private final User user;
    private final UserThreadsController controller;

    public UserThread(User user, UserThreadsController controller) {
        this.user = user;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            controller.addUserToList(user);
            MessageFacade messageFacade = new MessageFacade(controller);
            while (!Thread.interrupted()) {
                try {
                    processMessages(messageFacade.processIncomingMessage(user.getMessage(), user.getName()));
                } catch (MessageFacadeException ex) {
                    if (Objects.equals(ex.getMessage(), "User message length > 150")
                            || Objects.equals(ex.getMessage(), "User name is already taken")) {
                        System.out.println(ex.getMessage());
                        try {
                            controller.sendMessageToUser(user, ex.getMessage());
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
            controller.removeUserFromList(user);
            user.close();
            System.out.println("Connection with user is closed\n");
        }
    }

    public void processMessages(Message msg) throws ServerException {
        if (msg instanceof SendMessage) {
            controller.saveMessage(msg);
            controller.sendMessageToAllUsers(msg.toString());
        }
        if (msg instanceof HistoryMessage) {
            controller.sendMessageHistoryToUser(user);
        }
        if (msg instanceof SetUserNameMessage) {
            user.setName(msg.getData());
        }

    }


}