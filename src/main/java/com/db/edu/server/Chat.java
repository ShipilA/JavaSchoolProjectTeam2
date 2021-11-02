package com.db.edu.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat{

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9222);
            while(true){
                Socket socket = serverSocket.accept();
                UserThread userThread = new UserThread(
                        new User(socket),
                        new Room("default"));
                Thread thread = new Thread(userThread);
                thread.start();
            }
        } catch (IOException e) {
            //TODO add logger
        }
    }
}