package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreen implements Initializable, ControlledScreen {

    ScreensController myController;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    protected void onActionSearchNewBook (ActionEvent event){
        myController.setScreen("scene1");


    }
    @FXML protected void onActionSearchBook (ActionEvent event){
        myController.setScreen("scene2");
    }
}
