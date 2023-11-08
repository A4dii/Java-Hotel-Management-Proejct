package ui;

import model.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class CustomerInputDialog extends Dialog<Customer> {
    private TextField idField = new TextField();
    private TextField nameField = new TextField();
    private TextField contactInfoField = new TextField();

    public CustomerInputDialog() {
        setTitle("Add Customer");
        setHeaderText("Enter Customer Information");

        // Create a grid for the dialog layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        // Add input fields to the grid
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Contact Info:"), 0, 2);
        grid.add(contactInfoField, 1, 2);

        // Enable the "Add" button when valid data is entered
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            getDialogPane().lookupButton(ButtonType.OK).setDisable(newValue.trim().isEmpty());
        });

        // Set the dialog content
        getDialogPane().setContent(grid);

        // Convert the result to a Customer object when the "Add" button is clicked
        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                int id = Integer.parseInt(idField.getText());
                return new Customer(id, nameField.getText(), contactInfoField.getText());
            }
            return null;
        });
    }

    public Customer getCustomer() {
        int id = Integer.parseInt(idField.getText());
        return new Customer(id, nameField.getText(), contactInfoField.getText());
    }
}

