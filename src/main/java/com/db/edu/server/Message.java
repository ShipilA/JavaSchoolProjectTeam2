package com.db.edu.server;

import java.sql.Time;

public interface Message {
    public Boolean isSaved();

    public String getUserName();

    public String getText();

    public Time getTime();

    public String getMessage();

}
