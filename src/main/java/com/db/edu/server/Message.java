package com.db.edu.server;


import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.lineSeparator;

public class Message {
    private String time;
    private String userName;
    private String text;


    public Message(String name, String messageText) {
        time = timeStamp();
        userName = name;
        text = messageText;

    }
    public Message(String incommingMessage) {
        time = timeStamp();
        fromIncommingMessage(incommingMessage);
    }

    public String toString() {
        return String.format("%s %s" + lineSeparator() + "%s", time, userName, text);
    }

    public String toCSVLine(String separator) {
        return String.format("%s" + separator + "%s" + separator + "%s", time, userName, text);
    }

    public void fromCSVLine(String data) {
        String[] items = data.split(";");
        time = items[0];
        userName = items[1];
        text = items[2];
    }

    public void fromIncommingMessage(String message){
        String[] items = message.split("\t");
        userName = items[0];
        text = items[1];
    }

    private String timeStamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }

}
