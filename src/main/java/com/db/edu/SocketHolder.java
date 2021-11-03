package com.db.edu;

public class SocketHolder {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 9999;

    private SocketHolder() {
    }

    public static String getADDRESS() {
        return ADDRESS;
    }

    public static int getPORT() {
        return PORT;
    }
}
