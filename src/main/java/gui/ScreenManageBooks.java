package gui;

import database.Book;
import database.BookPersisting;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ScreenManageBooks implements Initializable, ControlledScreen {

    ScreensController myController;

    private static  Book currentBook = new Book();

    @FXML
    TableView booksList;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void onActionBack (ActionEvent event){
        System.out.println("++++++++button BACK pressed+++++++++");
        myController.setScreen("main");
    }

    @FXML
    private void find(){
        System.out.println("++++++++button FIND pressed+++++++++");
        BookPersisting bookPersisting = new BookPersisting();

        ObservableList<Book> data = booksList.getItems();
        data.clear();
        data.addAll(bookPersisting.getBooks());
    }

    @FXML
    public void onActionDelete(ActionEvent actionEvent) {
        BookPersisting bookPersisting = new BookPersisting();
        currentBook = (Book) booksList.getSelectionModel().getSelectedItem();
        bookPersisting.removeBook(currentBook);
        ObservableList<Book> data = booksList.getItems();
        data.clear();
        data.addAll(bookPersisting.getBooks());
    }

    public void onActionEdit(ActionEvent actionEvent) {
        currentBook = (Book) booksList.getSelectionModel().getSelectedItem();
        myController.setScreen("scene3");

    }

    public void onActionAdd(ActionEvent actionEvent) {
    }


    public static Book getCurrentBook() {
        return currentBook;
    }

    public static void setCurrentBook(Book currentBook) {
        ScreenManageBooks.currentBook = currentBook;
    }

}
