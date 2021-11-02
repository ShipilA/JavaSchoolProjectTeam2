package com.db.edu.client;

import java.net.Socket;
import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 9222);
            Thread threadIn = new Thread(new SocketInputThread(s));
            Thread threadOut = new Thread(new SocketOutputThread(s));
            threadIn.start();
            threadOut.start();
        } catch (IOException ex) {
            //TODO add logger
        }
    }
}

