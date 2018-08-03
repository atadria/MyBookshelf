package gui;

import database.Book;
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
    public TextField title = new TextField();
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
