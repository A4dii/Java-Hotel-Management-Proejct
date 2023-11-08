package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class RoomController {
    private List<Room> rooms = new ArrayList<>();
    private static final String ROOM_CSV_FILE = "util/rooms.csv";

    public RoomController() {
        loadRoomsFromCSV();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        saveRoomsToCSV();
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    // Implement other room-related methods as needed

    private void loadRoomsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ROOM_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int roomNumber = Integer.parseInt(data[0]);
                String roomType = data[1];
                double price = Double.parseDouble(data[2]);
                boolean isAvailable = Boolean.parseBoolean(data[3]);
                rooms.add(new Room(roomNumber, roomType, price, isAvailable));
            }
        } catch (IOException e) {
            System.err.println("Error loading room data from rooms.csv: " + e.getMessage());
        }
    }

    private void saveRoomsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROOM_CSV_FILE))) {
            for (Room room : rooms) {
                String roomData = String.format("%d,%s,%.2f,%b",
                        room.getRoomNumber(),
                        room.getRoomType(),
                        room.getPrice(),
                        room.checkAvailabilty());
                writer.write(roomData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving room data to rooms.csv: " + e.getMessage());
        }
    }
}

