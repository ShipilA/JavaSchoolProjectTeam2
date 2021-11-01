package com.db.edu.server;

import com.db.edu.server.exception.ServerException;

public class SocketThread implements Runnable {

    private final User user;
    private final UserList userList;

    public SocketThread(User user, UserList userList) {
        this.user = user;
        this.userList = userList;
    }

    @Override
    public void run() {
        try {
            userList.addUserToList(user);
            userList.sendMessageToAllOtherUsers(user);
            userList.removeUserFromList(user);
            user.close();
        } catch (ServerException ex) {
            //TODO add logger
        }
    }
}