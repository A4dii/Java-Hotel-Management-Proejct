package ui;

import controller.*;
import model.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class CustomerManagementStage extends Stage {
    private CustomerController customerController;
    private TableView<Customer> customerTable;

    public CustomerManagementStage(CustomerController customerController) {
        this.customerController = customerController;
        setTitle("Customer Management");
        
        customerTable = new TableView<>();
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Customer, String> contactInfoColumn = new TableColumn<>("Contact Info");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        customerTable.getColumns().addAll(nameColumn, contactInfoColumn);

        // Initialize customerTable with data from the customerController
        customerTable.getItems().addAll(customerController.getAllCustomers());

        Button addCustomerButton = new Button("Add Customer");
        addCustomerButton.setOnAction(e -> openAddCustomerDialog());

        Button deleteCustomerButton = new Button("Delete Customer");
        deleteCustomerButton.setOnAction(e -> deleteSelectedCustomer());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addCustomerButton, deleteCustomerButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(customerTable, buttonBox);

        Scene scene = new Scene(layout, 600, 400);
        setScene(scene);
    }

    private void openAddCustomerDialog() {
        // Implement code to open a dialog for adding a new customer
        // Example: Create a new stage with input fields for customer details
        CustomerInputDialog dialog = new CustomerInputDialog();
        dialog.showAndWait().ifPresent(result -> {
            Customer newCustomer = dialog.getCustomer();
            customerController.addCustomer(newCustomer);
            customerTable.getItems().add(newCustomer); // Add the new customer to the table
        });
    }

    private void deleteSelectedCustomer() {
        // Implement code to delete the selected customer
        // Example: Get the selected customer from the table and use customerController to delete it
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customerController.deleteCustomer(selectedCustomer);
            customerTable.getItems().remove(selectedCustomer); // Remove the customer from the table
        }
    }
}
