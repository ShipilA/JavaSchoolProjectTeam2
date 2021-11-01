package com.db.edu.server;

import com.db.edu.server.database.RoomMessagesDB;

import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ListSocket {

    private static ArrayList<Socket> listSocket = new ArrayList<Socket>();

    private static ArrayList<RoomMessagesDB> roomMessagesDB = new ArrayList<>();

    public synchronized static ArrayList<RoomMessagesDB> getListRoomMessagesDB() {
        return ListSocket.roomMessagesDB;
    }

    public synchronized static void addNewRoomMessagesDB(File roomFile) {
        ListSocket.roomMessagesDB.add(new RoomMessagesDB(roomFile));
    }

    public synchronized static ArrayList<Socket> getListSocket() {
        return ListSocket.listSocket;
    }

    public synchronized static void addSocketToList(Socket s) {
        ListSocket.listSocket.add(s);
    }

    public synchronized static void removeSocketWithList(Socket s) {
        ListSocket.listSocket.remove(s);
    }
}