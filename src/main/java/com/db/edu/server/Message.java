package com.db.edu.server;


import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

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

    public Message(String csvLine) {
        fromCSVLine(csvLine);
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

    private String timeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
