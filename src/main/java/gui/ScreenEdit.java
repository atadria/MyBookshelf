package gui;

import database.Book;
import database.BookPersisting;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ScreenEdit implements Initializable, ControlledScreen {

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
        System.out.println("++++++++button BACK pressed+++++++++");

        myController.setScreen("scene2");
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

        myController = screenPage;

    }

    public void onActionSubmit(ActionEvent actionEvent) {
        BookPersisting bookPersisting = new BookPersisting();
        String[] list = new String[7];
        list[0] = title.getText();
        list[1] = subtitle.getText();
        list[2] = author.getText();
        list[3] = publisher.getText();
        list[4] = info.getText();
        list[5] = isbn.getText();
        list[6] = date.getText();
        bookPersisting.editBook(ScreenManageBooks.getCurrentBook(), list);
    }


    public void onActionRefresh(ActionEvent actionEvent) {
        title.setText(ScreenManageBooks.getCurrentBook().getTitle());
        subtitle.setText(ScreenManageBooks.getCurrentBook().getSubtitle());
        author.setText(ScreenManageBooks.getCurrentBook().getAuthor());
        publisher.setText(ScreenManageBooks.getCurrentBook().getPublisher());
        info.setText(ScreenManageBooks.getCurrentBook().getAdditionalInfo());
        isbn.setText(ScreenManageBooks.getCurrentBook().getISBN());
        date.setText(ScreenManageBooks.getCurrentBook().getDate());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
