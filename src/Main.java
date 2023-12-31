import controller.CustomerController;
import controller.EmployeeController;
import controller.ReservationController;
import controller.RoomController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class Main extends Application {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private Stage primaryStage;

    private CustomerController customerController;
    private EmployeeController employeeController;
    private RoomController roomController;
    private ReservationController reservationController;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hotel Management System");

        // Create instances of controllers
        customerController = new CustomerController();
        employeeController = new EmployeeController();
        roomController = new RoomController();
        reservationController = new ReservationController(customerController, roomController);


        // Create a login scene
        createLoginScene();
        
        primaryStage.show();
    }

    private void createLoginScene() {
        VBox loginLayout = new VBox(10);
        
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            if (isAdmin(username, password)) {
                createAdminScene();
            } else {
                // Show an error message or handle invalid login
                System.out.println("Invalid login");
            }
        });
        
        loginLayout.getChildren().addAll(usernameField, passwordField, loginButton);
        
        Scene loginScene = new Scene(loginLayout, 300, 200);
        primaryStage.setScene(loginScene);
    }

    private boolean isAdmin(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    private void createAdminScene() {
        // Here, you can create the admin scene with access to the controller files and other functionalities
        // You can use different layouts, buttons, and navigation as needed
        // For example, you can create buttons to access CustomerController, EmployeeController, RoomController, and ReservationController functionalities.
        HBox adminLayout = new HBox(10);
        Button customerButton = new Button("Customer Controller");
        Button employeeButton = new Button("Employee Controller");
        Button roomButton = new Button("Room Controller");
        Button reservationButton = new Button("Reservation Controller");
        
        customerButton.setOnAction(e -> {
            // Implement functionality for the Customer Controller
            // For example, open a new window to manage customers
            openCustomerManagementWindow();
        });

        employeeButton.setOnAction(e -> {
            // Implement functionality for the Employee Controller
            // For example, open a new window to manage employees
            openEmployeeManagementWindow();
        });

        roomButton.setOnAction(e -> {
            // Implement functionality for the Room Controller
            // For example, open a new window to manage rooms
            openRoomManagementWindow();
        });

        reservationButton.setOnAction(e -> {
            // Implement functionality for the Reservation Controller
            // For example, open a new window to manage reservations
            openReservationManagementWindow();
        });
        
        adminLayout.getChildren().addAll(customerButton, employeeButton, roomButton, reservationButton);
        Scene adminScene = new Scene(adminLayout, 600, 400);
        primaryStage.setScene(adminScene);
    }

    // Methods to open management windows for each controller
    private void openCustomerManagementWindow() {
        // Implement code to open a new window for managing customers
        // You can use JavaFX Stages and Scenes for this purpose
        // Example: new CustomerManagementStage(customerController).show();
    }

    private void openEmployeeManagementWindow() {
        // Implement code to open a new window for managing employees
        // Example: new EmployeeManagementStage(employeeController).show();
    }

    private void openRoomManagementWindow() {
        // Implement code to open a new window for managing rooms
        // Example: new RoomManagementStage(roomController).show();
    }

    private void openReservationManagementWindow() {
        // Implement code to open a new window for managing reservations
        // Example: new ReservationManagementStage(reservationController).show();
    }
}
