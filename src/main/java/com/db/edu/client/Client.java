package com.db.edu.client;

import com.db.edu.SocketHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SocketHolder.getADDRESS(), SocketHolder.getPORT());
            Thread threadIn = new Thread(new SocketInputThread(socket));
            Thread threadOut = new Thread(new SocketOutputThread(socket));
            threadIn.start();
            threadOut.start();
        } catch (IOException ex) {
            log.error("Failed to process connection: ", ex);
        }
    }
}

