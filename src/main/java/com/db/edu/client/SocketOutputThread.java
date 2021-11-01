package com.db.edu.client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketOutputThread implements Runnable {

    private Socket s = null;
    private Scanner in = null;
    private PrintWriter out = null;
    private boolean exit = true;
    private String inMessage = null;
    private String outMessage = null;
    private String clientName = "";

    public SocketOutputThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {

            System.out.println();

            Scanner in = new Scanner(System.in);
            System.out.print("Please enter your name: ");
            clientName = in.nextLine();

            out = new PrintWriter(s.getOutputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                outMessage = buffer.readLine();
                out.println(clientName+"\t"+outMessage);
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketOutputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}