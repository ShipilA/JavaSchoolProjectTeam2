package com.db.edu.server.user;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.rooms.Room;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class User {
    private String name = "Default name";
    private final Socket socket;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public User(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() throws ServerException {
        try {
            return socket.getOutputStream();
        } catch (IOException | NoSuchElementException e) {
            throw new ServerException("Exception in reading from user's socket", e);
        }
    }

    public String getMessage() throws ServerException {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            return scanner.nextLine();
        } catch (IOException | NoSuchElementException e) {
            throw new ServerException("Exception in reading from user's socket", e);
        }
    }

    public void close() throws ServerException {
        try {
            socket.close();
        } catch (IOException | NoSuchElementException e) {
            throw new ServerException("Exception in closing user's socket", e);
        }
    }

    public void chatInRoom(Room room) {
        UserThread userThread = new UserThread(this, room);
        Thread thread = new Thread(userThread);
        thread.start();
    }

    public void receiveMessage(String message) throws ServerException {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(message);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new ServerException("Exception in reading from user's socket", e);
        }
    }
}
