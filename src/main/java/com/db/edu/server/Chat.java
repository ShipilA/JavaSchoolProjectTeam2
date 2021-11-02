package com.db.edu.server;

import com.db.edu.server.rooms.RoomContainer;
import com.db.edu.server.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat {
    private static final Logger log = LoggerFactory.getLogger(Chat.class);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9222);
            RoomContainer roomContainer = new RoomContainer();
            while (true) {
                Socket socket = serverSocket.accept();
                User user = new User(socket);
                //TODO refactor room choosing
                user.chatInRoom(roomContainer.getRoomWithName("Default room"));
            }
        } catch (IOException e) {
            log.error("Failed to run server: {}", e);
        }
    }
}