package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class CustomerController {
    private List<Customer> customers = new ArrayList<>();
    private static final String CUSTOMER_CSV_FILE = "util/customers.csv";

    public CustomerController() {
        loadCustomersFromCSV();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        saveCustomersToCSV();
    }

    public void deleteCustomer(Customer customer) {
        customers.remove(customer); // Remove the customer from the list
        saveCustomersToCSV(); // Update the CSV file
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    // Implement other customer-related methods as needed

    private void loadCustomersFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String contactInfo = data[2];
                customers.add(new Customer(id, name, contactInfo));
            }
        } catch (IOException e) {
            System.err.println("Error loading customer data from customers.csv: " + e.getMessage());
        }
    }

    private void saveCustomersToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_CSV_FILE))) {
            for (Customer customer : customers) {
                String customerData = String.format("%d,%s,%s", customer.getId(), customer.getName(), customer.getContactInfo());
                writer.write(customerData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving customer data to customers.csv: " + e.getMessage());
        }
    }
}
