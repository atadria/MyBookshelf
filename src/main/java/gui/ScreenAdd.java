package gui;

import database.BookPersisting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ScreenAdd implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    public TextField title;
    @FXML
    public TextField subtitle;
    @FXML
    public TextField author;
    @FXML
    public TextField publisher;
    @FXML
    public TextArea info;
    @FXML
    public TextField isbn;
    @FXML
    public TextField date;

    public void onActionBack(ActionEvent actionEvent) {
        myController.setScreen("scene2");
    }

    public void onActionAdd(ActionEvent actionEvent) {
        BookPersisting bookPersisting = new BookPersisting();
        String[] list = new String[7];
        list[0] = title.getText();
        list[1] = subtitle.getText();
        list[2] = author.getText();
        list[3] = publisher.getText();
        list[4] = info.getText();
        list[5] = isbn.getText();
        list[6] = date.getText();
        bookPersisting.addBook(list);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
