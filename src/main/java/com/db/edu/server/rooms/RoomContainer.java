package com.db.edu.server.rooms;

import java.util.ArrayList;
import java.util.List;

public class RoomContainer {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }


}
