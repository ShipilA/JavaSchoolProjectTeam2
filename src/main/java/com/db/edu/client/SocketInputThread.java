package com.db.edu.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketInputThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(SocketInputThread.class);
    private final Socket socket;

    public SocketInputThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            while (!Thread.interrupted()) {
                if (scanner.hasNext()) {
                    String message = scanner.nextLine();
                    System.out.println(message);
                }
            }
        } catch (IOException ex) {
            log.error("Failed to process connection: ", ex);
        }
    }
}