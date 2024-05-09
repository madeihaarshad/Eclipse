package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

        // Create buttons for add, edit, and delete
        Button addButton = new Button("Add Contact");
        addButton.getStyleClass().add("button");
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

        Button editButton = new Button("Edit Contact");
        editButton.getStyleClass().add("button");
        editButton.setOnAction(event -> {
            Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                    selectedContact.setName(name);
                    selectedContact.setPhoneNumber(phone);
                    selectedContact.setEmail(email);
                    tableView.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in all fields for editing.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please select a contact to edit.");
                alert.showAndWait();
            }
        });

        Button deleteButton = new Button("Delete Contact");
        deleteButton.getStyleClass().add("button");
        deleteButton.setOnAction(event -> {
            Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                tableView.getItems().remove(selectedContact);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please select a contact to delete.");
                alert.showAndWait();
            }
        });

        // Create grid pane layout
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, nameLabel, nameField);
        gridPane.addRow(1, phoneLabel, phoneField);
        gridPane.addRow(2, emailLabel, emailField);

        // Add buttons to a VBox for styling
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton);
        gridPane.add(buttonBox, 1, 3);

        // Create scene
        Scene scene = new Scene(new VBox(createHeading(), gridPane, tableView), 500, 400);
        
        // Add CSS file to the scene
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        // Set stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Address Book");
        primaryStage.show();
    }

    private Label createHeading() {
        Label heading = new Label("Address Book");
        heading.getStyleClass().add("heading");
        return heading;
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

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
