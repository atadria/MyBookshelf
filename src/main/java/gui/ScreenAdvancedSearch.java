package gui;

import database.Book;
import database.BookPersisting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ScreenAdvancedSearch implements Initializable, ControlledScreen {

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

    @FXML
    private void onActionBack (ActionEvent event){
        myController.setScreen("scene2");
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

        myController = screenPage;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onActionSearch(ActionEvent event) {
        String[] list = new String[7];
        list[0] = title.getText();
        list[1] = subtitle.getText();
        list[2] = author.getText();
        list[3] = publisher.getText();
        list[4] = info.getText();
        list[5] = isbn.getText();
        list[6] = date.getText();

        BookPersisting bookPersisting = new BookPersisting();
        List<Book> result = bookPersisting.advancedSearch(list);
        ScreenManageBooks.setData(result);
        myController.setScreen("scene2");
    }
}
