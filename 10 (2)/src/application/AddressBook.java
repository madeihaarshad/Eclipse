package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddressBook extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create labels
        Label nameLabel = new Label("Name:");
        Label phoneLabel = new Label("Phone Number:");
        Label emailLabel = new Label("Email:");

        // Create text fields
        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();

        // Create TableView to display contacts
        TableView<Contact> tableView = new TableView<>();
        TableColumn<Contact, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Contact, String> phoneColumn = new TableColumn<>("Phone Number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        TableColumn<Contact, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.getColumns().addAll(nameColumn, phoneColumn, emailColumn);

        // Create button to add contact
        Button addButton = new Button("Add Contact");
        addButton.setOnAction(event -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
            } else {
                Contact contact = new Contact(name, phone, email);
                tableView.getItems().add(contact);
                nameField.clear();
                phoneField.clear();
                emailField.clear();
            }
        });

        // Create grid pane layout
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, nameLabel, nameField);
        gridPane.addRow(1, phoneLabel, phoneField);
        gridPane.addRow(2, emailLabel, emailField);
        gridPane.add(addButton, 1, 3);
        // Create scene
        Scene scene = new Scene(new javafx.scene.layout.VBox(gridPane, tableView), 500, 400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        // Set stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Address Book");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Contact class
    public static class Contact {
        private String name;
        private String phoneNumber;
        private String email;

        public Contact(String name, String phoneNumber, String email) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }
        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
        public String getEmail() {
            return email;
        }
    }
}
