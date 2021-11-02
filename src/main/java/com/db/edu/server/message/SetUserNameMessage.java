package com.db.edu.server.message;

public class SetUserNameMessage extends Message {

    public SetUserNameMessage(String fromUserName, String newName) {
        super(fromUserName,newName);
    }
}