package com.db.edu.server;

import com.db.edu.SocketHolder;
import com.db.edu.server.user.User;
import com.db.edu.server.user.UserThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Chat {
    final static Logger log = LoggerFactory.getLogger(Chat.class);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SocketHolder.getPORT())) {
            UserThreadsController controller = new UserThreadsController();

            log.info("Server successfully started");
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                User user = new User(socket);
                log.info("New user connected");
                user.startChat(controller);
            }
        } catch (IOException e) {
            log.error("Failed to run server: ", e);
        }
    }
}