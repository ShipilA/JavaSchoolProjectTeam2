package com.db.edu.server.database;

import com.db.edu.server.Message;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MessagesDB implements DataBase {
    private List<Message> data;
    private File tableFile;

    @Override
    public void saveMessage(Message message) {
        data.add(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return data;
    }

    @Override
    public Message getMessagesWithId(int index) {
        return data.get(index);
    }

    @Override
    public void saveDataToTable() {
        try (FileWriter writer = new FileWriter(tableFile.getAbsoluteFile(), false)) {

            data.forEach(message -> {
                try {
                    writer.append(message.toCSVLine(";"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void loadDataFromTable() {
        try (FileReader reader = new FileReader(tableFile.getAbsoluteFile())) {
            //TODO read CSV file
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
