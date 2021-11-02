package com.db.edu.server.user;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.rooms.Room;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class User {
    private final Socket socket;

    public User(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() throws ServerException {
        try {
            return socket.getOutputStream();
        } catch (IOException e) {
            throw new ServerException("Exception in reading from user's socket", e);
        }
    }

    public String getMessage() throws ServerException {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            return scanner.nextLine();
        } catch (IOException e) {
            throw new ServerException("Exception in reading from user's socket", e);
        }
    }

    public void close() throws ServerException {
        try {
            socket.close();
        } catch (IOException e) {
            throw new ServerException("Exception in closing user's socket", e);
        }
    }

    public void chatInRoom(Room room) {
        UserThread userThread = new UserThread(this, room);
        Thread thread = new Thread(userThread);
        thread.start();
    }
}
