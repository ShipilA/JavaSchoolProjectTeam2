package com.db.edu.client;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketOutputThread implements Runnable {

    private Socket s = null;
    private Scanner in = null;
    private PrintWriter out = null;
    private boolean exit = true;
    private String inMessage = null;
    private String outMessage = null;

    public SocketOutputThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(s.getOutputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                outMessage = buffer.readLine();
                out.println(outMessage);
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketOutputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}