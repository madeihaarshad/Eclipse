package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentRegistrationSystem extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create labels
        Label nameLabel = new Label("Name:");
        Label idLabel = new Label("ID:");
        Label emailLabel = new Label("Email:");
        Label genderLabel = new Label("Gender:");
        Label termsLabel = new Label("I agree to the terms and conditions:");

        // Create text fields
        TextField nameField = new TextField();
        TextField idField = new TextField();
        TextField emailField = new TextField();

        // Create radio buttons for gender selection
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadioButton = new RadioButton("Male");
        RadioButton femaleRadioButton = new RadioButton("Female");
        maleRadioButton.setToggleGroup(genderGroup);
        femaleRadioButton.setToggleGroup(genderGroup);

        // Create checkbox for terms and conditions
        CheckBox termsCheckbox = new CheckBox();

        // Create TableView to display student data
        TableView<Student> tableView = new TableView<>();
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Student, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableView.getColumns().addAll(nameColumn, idColumn, emailColumn, genderColumn);

        // Create button to submit data
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String id = idField.getText();
            String email = emailField.getText();
            String gender = "";
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else if (femaleRadioButton.isSelected()) {
                gender = "Female";
            }
            if (name.isEmpty() || id.isEmpty() || email.isEmpty() || gender.isEmpty() || !termsCheckbox.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields and agree to the terms and conditions.");
                alert.showAndWait();
            } else {
                Student student = new Student(name, id, email, gender);
                tableView.getItems().add(student);
                nameField.clear();
                idField.clear();
                emailField.clear();
                genderGroup.selectToggle(null);
                termsCheckbox.setSelected(false);
            }
        });

        // Create grid pane layout
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, nameLabel, nameField);
        gridPane.addRow(1, idLabel, idField);
        gridPane.addRow(2, emailLabel, emailField);
        gridPane.addRow(3, genderLabel, maleRadioButton, femaleRadioButton);
        gridPane.addRow(4, termsLabel, termsCheckbox);
        gridPane.add(submitButton, 1, 5);

        // Create scene
        Scene scene = new Scene(new javafx.scene.layout.VBox(gridPane, tableView), 500, 400);

        // Set stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Registration Form");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Student class
    public static class Student {
        private String name;
        private String id;
        private String email;
        private String gender;

        public Student(String name, String id, String email, String gender) {
            this.name = name;
            this.id = id;
            this.email = email;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getGender() {
            return gender;
        }
    }
}

