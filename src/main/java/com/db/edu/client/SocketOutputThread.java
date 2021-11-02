package com.db.edu.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;

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
            while (!Thread.interrupted()) {
                String outMessage = in.nextLine();
                out.println(outMessage);
                out.flush();
            }
        } catch (IOException ex) {
            //TODO add logger
        }
    }
}