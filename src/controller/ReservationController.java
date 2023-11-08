package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.*;

public class ReservationController {
    private List<Reservation> reservations = new ArrayList<>();
    private static final String RESERVATION_CSV_FILE = "util/reservations.csv";

    private CustomerController customerController;
    private RoomController roomController;

    public ReservationController(CustomerController customerController, RoomController roomController) {
        this.customerController = customerController;
        this.roomController = roomController;
        loadReservationsFromCSV();
    }

    public void makeReservation(Customer customer, Room room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(generateReservationId(), customer, room, checkInDate, checkOutDate);
        reservations.add(newReservation);
        saveReservationsToCSV();
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = findReservationById(reservationId);
        if (reservation != null) {
            reservation.cancelReservation();
            saveReservationsToCSV();
        }
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    private Customer getCustomerById(int customerId) {
        // Access the CustomerController to get a customer by ID
        List<Customer> allCustomers = customerController.getAllCustomers();
        for (Customer customer : allCustomers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null; // Return null if no customer with the specified ID is found
    }

    private Room getRoomById(int roomId) {
        // Access the RoomController to get a room by ID
        List<Room> allRooms = roomController.getAllRooms();
        for (Room room : allRooms) {
            if (room.getRoomNumber() == roomId) {
                return room;
            }
        }
        return null; // Return null if no room with the specified ID is found
    }

    public Reservation findReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    private int generateReservationId() {
        // Implementation of generating a unique ID goes here
        return reservations.size() + 1;
    }

    private void loadReservationsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATION_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                int customerId = Integer.parseInt(data[1]); // Assuming CSV includes customer IDs
                int roomId = Integer.parseInt(data[2]); // Assuming CSV includes room IDs
                Date checkInDate = new Date(Long.parseLong(data[3]));
                Date checkOutDate = new Date(Long.parseLong(data[4]));
                String status = data[5];
                Customer customer = getCustomerById(customerId);
                Room room = getRoomById(roomId);
                reservations.add(new Reservation(id, customer, room, checkInDate, checkOutDate));
            }
        } catch (IOException e) {
            System.err.println("Error loading reservation data from reservations.csv: " + e.getMessage());
        }
    }

    private void saveReservationsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVATION_CSV_FILE))) {
            for (Reservation reservation : reservations) {
                String reservationData = String.format("%d,%d,%d,%d,%d,%s",
                        reservation.getId(),
                        reservation.getCustomer().getId(),
                        reservation.getRoom().getRoomNumber(),
                        reservation.getCheckInDate().getTime(),
                        reservation.getCheckOutDate().getTime(),
                        reservation.getStatus());
                writer.write(reservationData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving reservation data to reservations.csv: " + e.getMessage());
        }
    }

    public List<Reservation> getReservationsForCustomer(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    // Method to retrieve all reservations for a specific room
    public List<Reservation> getReservationsForRoom(Room room) {
        List<Reservation> roomReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                roomReservations.add(reservation);
            }
        }
        return roomReservations;
    }

    // Method to update the status of a reservation
    public void updateReservationStatus(int reservationId, String newStatus) {
        Reservation reservation = findReservationById(reservationId);
        if (reservation != null) {
            reservation.setStatus(newStatus);
            saveReservationsToCSV(); // Save the updated reservation to the CSV file
        }
    }

    // Method to check if a room is available for a given date range
    public boolean isRoomAvailable(Room room, Date checkInDate, Date checkOutDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                Date existingCheckInDate = reservation.getCheckInDate();
                Date existingCheckOutDate = reservation.getCheckOutDate();

                // Check for date range overlap
                if (checkInDate.before(existingCheckOutDate) && checkOutDate.after(existingCheckInDate)) {
                    return false; // Room is not available for the given date range
                }
            }
        }
        return true; // Room is available for the given date range
    }
}
