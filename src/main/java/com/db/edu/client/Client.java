package com.db.edu.client;
import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to Chat Client!");
            Socket s = new Socket("127.0.0.1",9222);
//            System.out.println("Connect to server...");
            Thread threadIn = new Thread(new SocketInputThread(s));
            Thread threadOut = new Thread(new SocketOutputThread(s));
            threadIn.start();
            threadOut.start();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

