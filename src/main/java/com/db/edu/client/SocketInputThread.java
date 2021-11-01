package com.db.edu.client;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketInputThread implements Runnable {

    private Socket s = null;
    private Scanner in = null;
    private PrintWriter out = null;
    private boolean exit = true;
    private String inMessage = null;
    private String outMessage = null;

    public SocketInputThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            in = new Scanner(s.getInputStream());
            while(true){
                if(in.hasNext()){
                    inMessage = in.nextLine();
                    System.out.println(inMessage);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}