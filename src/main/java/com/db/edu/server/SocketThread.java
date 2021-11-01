package com.db.edu.server;

import com.db.edu.server.database.RoomMessagesDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketThread implements Runnable {

    private Socket s = null;
    private Scanner in = null;
    private PrintWriter out = null;
    private boolean exit = true;
    private String inMessage = null;
    private String outMessage = null;
    private ArrayList<Socket> listSocket = null;
    private RoomMessagesDB roomMessagesDB = null;

    public SocketThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            System.out.println("User connected!");
            ListSocket.addSocketToList(s); //  добавление текущого сокета с глобальной список сокетов
            in = new Scanner(s.getInputStream());
            while (exit) {
                inMessage = in.nextLine();
                Message newMessage = new Message(inMessage);
//                for (:
//                     ) {
//
//                }
//                roomMessagesDB = ListSocket.getListRoomMessagesDB().get(0);
//                roomMessagesDB.saveMessage(newMessage);
                listSocket = ListSocket.getListSocket();
                for (Socket socket : listSocket) { // отсылка сообщения всем сокетам\клиентам
                    if (!socket.equals(s)) {
                        out = new PrintWriter(socket.getOutputStream());
                        out.println(newMessage.toString());
                        out.flush();
                    }
                }
                if (inMessage.trim().equals("exit")) {
                    exit = false;
                }
            }
            ListSocket.removeSocketWithList(s); // если поток завершается то сокет клиента удаляется из списка сокетов
            System.out.println("User disconnected!");
        } catch (IOException ex) {
            try {
                s.close();
            } catch (IOException ex1) {
                Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}