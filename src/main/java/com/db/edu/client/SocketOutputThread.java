package com.db.edu.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketOutputThread implements Runnable {

    private final Socket socket;

    public SocketOutputThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner in = new Scanner(System.in);
            while (true) {
                String outMessage = in.nextLine();
                out.println(outMessage);
                out.flush();
            }
        } catch (IOException ex) {
            //TODO add logger
        }
    }
}