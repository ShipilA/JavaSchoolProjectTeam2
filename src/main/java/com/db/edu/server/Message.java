package com.db.edu.server;


import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.lineSeparator;

public class Message {
    private String time;
    private String userName;
    private String key;
    private String data;

    public Message() {}

    public Message(String incomingMessage) {
        time = timeStamp();
        fromIncomingMessage(incomingMessage);
    }

    public String getData() {
        return data;
    }

    public String getKey() {
        return key;
    }

    public String toString() {
        return String.format("%s %s" + lineSeparator() + "%s", time, userName, data);
    }

    public String toCSVLine(String separator) {
        return String.format("%s" + separator + "%s" + separator + "%s", time, userName, data);
    }

    public Message fromCSVLine(String data) {
        String[] items = data.split(";");
        time = items[0];
        userName = items[1];
        this.data = items[2];
        return this;
    }

    public void fromIncomingMessage(String message) {
        String[] items = message.split(" ", 2);
        if (items.length>1){
            key = items[0];
            data = items[1];
        } else if (items.length>0){
            data = items[0];
        }
    }

    private String timeStamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }

}
