package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    public void handleButtonClick(ActionEvent event) {
        System.out.println("Button Clicked!");
    }
}
