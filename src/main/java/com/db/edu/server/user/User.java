package com.db.edu.server.user;

import com.db.edu.server.UserThreadsController;
import com.db.edu.server.exception.ServerException;

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

    public void close() {
        try {
            socket.close();
        } catch (IOException | NoSuchElementException e) {
            System.out.println("Socket connection is closed\n");
        }
    }

    public UserThread startChat(UserThreadsController controller) {
        UserThread userThread = new UserThread(this, controller);
        Thread thread = new Thread(userThread);
        thread.start();
        return userThread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return socket.equals(user.socket) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, name);
    }
}
