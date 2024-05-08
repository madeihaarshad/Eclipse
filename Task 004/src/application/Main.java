package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create parent and child UI components
        VBox vbox = new VBox();
        Button btn = new Button("Child!");
        Button parentBtn = new Button("Parent!");

        // Add event handler to the parent button
        parentBtn.setOnAction(e -> {
            System.out.println("Parent Clicked!");

            // Open file chooser dialog for saving a file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            File file = fileChooser.showSaveDialog(primaryStage);

            // Write content to the selected file
            if (file != null) {
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.println("Hello, File!");
                    System.out.println("Content saved to: " + file.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add child component to the parent
        vbox.getChildren().addAll(btn, parentBtn);

        // Add event handler to the child button
        btn.setOnAction(e -> {
            System.out.println("Child Clicked!");

            // Open file chooser dialog for opening a file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            File file = fileChooser.showOpenDialog(primaryStage);

            // Read content from the selected file
            if (file != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    System.out.println("File content:");
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add parent component to the scene
        Scene scene = new Scene(vbox, 200, 200);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Event Propagation & File Handling Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
