package com.db.edu.server.user;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.rooms.Room;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class User {
    private final Socket socket;
    private String name = "Default name";

    public User(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(socket.getPort(), user.socket.getPort()) &&
                Objects.equals(socket.getLocalAddress(), user.socket.getLocalAddress()) &&
                Objects.equals(socket.getLocalPort(), user.socket.getLocalPort()) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, name);
    }
}
