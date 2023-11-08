package model;
import java.util.Date;

public class Reservation {
    private int id;
    private Customer customer;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;

    public Reservation(int id, Customer customer, Room room, Date checkInDate, Date checkOutDate) {
        this.id = id;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = "Active"; // You can set the initial status as "Active" when a reservation is created.
    }

    // Getters and setters for Reservation attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Additional methods to update and manage reservations
    public void cancelReservation() {
        // You can implement this method to change the reservation status to "Canceled."
        this.status = "Canceled";
    }

}
