package com.db.edu.client;

import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;

public class SocketInputThread implements Runnable {

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
            //TODO add logger
        }
    }
}