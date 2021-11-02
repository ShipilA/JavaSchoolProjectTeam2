package com.db.edu.client;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketInputThread implements Runnable {

    private final Socket socket;

    public SocketInputThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            while(true){
                if(scanner.hasNext()){
                    String message = scanner.nextLine();
                    System.out.println(message);
                }
            }
        } catch (IOException ex) {
            //TODO add logger
        }
    }
}