package com.db.edu.server.database;

import com.db.edu.server.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;

public class RoomMessagesDB implements DataBase {
    static final Logger log = LoggerFactory.getLogger(RoomMessagesDB.class);
    private final File tableFile;

    public RoomMessagesDB(String roomName) {
        tableFile = new File(roomName + ".csv");
    }

    @Override
    public void saveMessage(Message message) {
        appendDataToTable(message.toCSVLine(";"));
    }

    @Override
    public String getAllMessages() {
        return getDataFromTable();
    }

    private void appendDataToTable(String data) {
        try {
            if (!tableFile.exists() && !tableFile.createNewFile()) {
                throw new IOException("Couldn't create file\n");
            }
            Files.write(tableFile.toPath(), (data + lineSeparator()).getBytes(StandardCharsets.UTF_8), WRITE, APPEND);
        } catch (IOException e) {
            log.error("Error ", e);
        }
    }

    private String getDataFromTable() {
        StringBuilder out = new StringBuilder();
        try (Stream<String> lines = Files.lines(tableFile.toPath())) {
            lines.forEach(s -> out.append(new Message().fromCSVLine(s)).append(lineSeparator()));
        } catch (IOException e) {
            log.error("Error ", e);
        }

        return out.toString();
    }
}
