package com.db.edu.server.rooms;

import com.db.edu.server.exception.ServerException;

import java.util.LinkedList;
import java.util.List;

public class RoomContainer {
    private final List<Room> rooms = new LinkedList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

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

}