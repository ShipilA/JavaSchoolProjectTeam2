package com.db.edu.server.rooms;

import java.util.LinkedList;
import java.util.List;

public class RoomContainer {
    private final List<Room> rooms = new LinkedList<>();

    public Room getRoomWithName(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        Room room = new Room(name);
        rooms.add(room);
        return room;
    }

    public boolean isNameTaken(String name) {
        return rooms.stream().anyMatch(room -> room.isNameTaken(name));
    }

}
