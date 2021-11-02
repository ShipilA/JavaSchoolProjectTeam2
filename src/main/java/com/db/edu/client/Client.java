package com.db.edu.client;

import com.db.edu.server.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
import java.io.IOException;

public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {

        try {
            Socket s = new Socket("127.0.0.1", 9222);
            Thread threadIn = new Thread(new SocketInputThread(s));
            Thread threadOut = new Thread(new SocketOutputThread(s));
            threadIn.start();
            threadOut.start();
        } catch (IOException ex) {
            log.error("Failed to process connection: {}", ex);
        }
        log.warn("ProgramFinished");
    }
}

