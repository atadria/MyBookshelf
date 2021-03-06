package gui;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import database.Book;
import database.BookPersisting;
import googlesearch.BooksFinder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ScreenFindBooks implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    private TableView booksList = new TableView();
    @FXML
    private TextField searchedItem;

    private static ObservableList<Book> data;

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void onActionBack(ActionEvent event) {
        myController.setScreen("main");
    }

    @FXML
    private void find() {
        data= booksList.getItems();
        data.clear();

        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        try {
            BooksFinder.queryGoogleBooks(jsonFactory, searchedItem.getText(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onActionAdd() {
        Book selectedBook = (Book) booksList.getSelectionModel().getSelectedItem();
        BookPersisting bookPersisting = new BookPersisting();
        bookPersisting.saveBook(selectedBook);

//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setTitle("How many books do you want to add?");
//        dialog.setContentText("Please enter number of books:");
//
//        Optional<String> result = dialog.showAndWait();
//        result.ifPresent(numberOfBooks -> System.out.println("Number of books: " + numberOfBooks ));
    }

    @FXML
    public void keyListener(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
            find();
        }
    }

    public void addOnEnter(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
            onActionAdd();
        }
    }

    public void setData(ObservableList<Book> data) {
        this.data = data;
    }
}
