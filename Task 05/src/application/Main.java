package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create parent and child UI components
        VBox vbox = new VBox();
        Button btn = new Button("Child!");
        Button parentBtn = new Button("Parent!");

        // Create a date picker
        DatePicker datePicker = new DatePicker();

        // Add the date picker to the VBox
        vbox.getChildren().addAll(datePicker, btn, parentBtn);

        // Add event handler to the parent button
        parentBtn.setOnAction(e -> {
            System.out.println("Parent Clicked!");
            System.out.println("Selected Date: " + datePicker.getValue());
        });

        // Add event handler to the child button
        btn.setOnAction(e -> {
            System.out.println("Child Clicked!");
            System.out.println("Selected Date: " + datePicker.getValue());
        });

        // Create the scene
        Scene scene = new Scene(vbox, 200, 200);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Date Picker Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
