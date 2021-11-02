package com.db.edu.server;

import com.db.edu.server.rooms.RoomContainer;
import com.db.edu.server.user.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat {

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
            //TODO add logger
        }
    }
}