package com.db.edu.server;


import java.time.LocalTime;

import static java.lang.System.lineSeparator;

public class Message {
    private boolean saveStatus = false;

    private String userName;
    private LocalTime time;
    private String text;

    public Boolean isSaved() {
        return saveStatus;
    }

    public String toString() {
        return String.format(new String("%s %s" + lineSeparator() + "%s"), userName, time.toString(), text);
    }

    public String toCSVLine(String separator) {
        return String.format(new String("%s"+separator+"%s"+separator+"%s"), userName, time.toString(), text);
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public LocalTime getTime() {
        return time;
    }

}
