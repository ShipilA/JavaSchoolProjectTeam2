package com.db.edu.server.database;

import com.db.edu.server.message.Message;

public interface DataBase {
    public void saveMessage(Message message);

    public String getAllMessages();

}
