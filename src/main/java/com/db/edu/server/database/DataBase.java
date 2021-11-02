package com.db.edu.server.database;

import com.db.edu.server.message.Message;

public interface DataBase {
    void saveMessage(Message message);

    String getAllMessages();

}
