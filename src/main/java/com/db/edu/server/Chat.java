package com.db.edu.server;

import com.db.edu.SocketHolder;
import com.db.edu.server.rooms.RoomContainer;
import com.db.edu.server.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat {
    final static Logger log = LoggerFactory.getLogger(Chat.class);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SocketHolder.getPORT());
            RoomContainer roomContainer = new RoomContainer();
            log.info("Server successfully started");
            while (true) {
                Socket socket = serverSocket.accept();
                User user = new User(socket, roomContainer);
                log.info("New user connected");
                //TODO refactor room choosing
                user.chatInRoom(roomContainer.getRoomWithName("Default room"));
            }
        } catch (IOException e) {
            log.error("Failed to run server: ", e);
        }
    }
}