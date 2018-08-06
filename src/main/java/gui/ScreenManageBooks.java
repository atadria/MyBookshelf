package gui;

import database.Book;
import database.BookPersisting;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ScreenManageBooks implements Initializable, ControlledScreen {

    ScreensController myController;

    private static  Book currentBook = new Book();

    @FXML
    TableView booksList;
    @FXML
    TextField searchedItem;

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
        data.addAll(bookPersisting.getBooks(searchedItem.getText()));
    }

    @FXML
    public void onActionDelete(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to delete this book?");
        currentBook = (Book) booksList.getSelectionModel().getSelectedItem();
        alert.setContentText(currentBook.getTitle());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            BookPersisting bookPersisting = new BookPersisting();
            currentBook = (Book) booksList.getSelectionModel().getSelectedItem();
            bookPersisting.removeBook(currentBook);
            ObservableList<Book> data = booksList.getItems();
            data.clear();
            data.addAll(bookPersisting.getBooks());
        }
    }

    public void onActionEdit(ActionEvent actionEvent) {
        currentBook = (Book) booksList.getSelectionModel().getSelectedItem();
        myController.setScreen("scene3");

    }

    public void onActionAdd(ActionEvent actionEvent) {
        myController.setScreen("scene4");
    }


    public static Book getCurrentBook() {
        return currentBook;
    }

    public static void setCurrentBook(Book currentBook) {
        ScreenManageBooks.currentBook = currentBook;
    }

}
