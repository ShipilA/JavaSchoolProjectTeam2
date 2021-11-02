package com.db.edu.client;

import com.db.edu.server.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;

public class SocketInputThread implements Runnable {
    private static Logger log = LoggerFactory.getLogger(Chat.class);
    private final Socket socket;
    static Logger log = LoggerFactory.getLogger(SocketInputThread.class);

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
            log.warn("ProgramFinished");
            //TODO add logger
            log.error("Failed to process connection: {}", ex);
        }
    }
}