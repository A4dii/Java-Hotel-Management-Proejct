// RoomInputDialog.javaendDate
package ui;

import model.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import model.Room;

public class RoomInputDialog extends Dialog<Room> {
    private TextField idField = new TextField();
    private TextField roomTypeField = new TextField();
    private TextField priceField = new TextField();

    public RoomInputDialog() {
        setTitle("Add Room");
        setHeaderText("Enter Room Information");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Room Type:"), 0, 1);
        grid.add(roomTypeField, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(priceField, 1, 2);

        idField.textProperty().addListener((observable, oldValue, newValue) -> {
            getDialogPane().lookupButton(ButtonType.OK).setDisable(newValue.trim().isEmpty());
        });

        getDialogPane().setContent(grid);

        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                int id = Integer.parseInt(idField.getText());
                String roomType = roomTypeField.getText();
                double price = Double.parseDouble(priceField.getText());
                return new Room(id, roomType, price);
            }
            return null;
        });
    }

    public Room getRoom() {
        int id = Integer.parseInt(idField.getText());
        String roomType = roomTypeField.getText();
        double price = Double.parseDouble(priceField.getText());
        return new Room(id, roomType, price);
    }
}
