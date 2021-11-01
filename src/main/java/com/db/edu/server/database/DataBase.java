package com.db.edu.server.database;

import com.db.edu.server.Message;

import java.util.List;

public interface DataBase {
    public void saveMessage(Message message);

    public List<Message> getAllMessages();

    public Message getMessagesWithId(int index);
}
