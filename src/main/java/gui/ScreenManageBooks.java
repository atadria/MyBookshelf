package gui;

import database.Book;
import database.BookPersisting;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.*;

public class ScreenManageBooks implements Initializable, ControlledScreen {

    ScreensController myController;

    private static Book currentBook = new Book();

    static ObservableList<Book> data = new ObservableList<Book>() {
        @Override
        public void addListener(ListChangeListener<? super Book> listener) {

        }

        @Override
        public void removeListener(ListChangeListener<? super Book> listener) {

        }

        @Override
        public boolean addAll(Book... elements) {
            return false;
        }

        @Override
        public boolean setAll(Book... elements) {
            return false;
        }

        @Override
        public boolean setAll(Collection<? extends Book> col) {
            return false;
        }

        @Override
        public boolean removeAll(Book... elements) {
            return false;
        }

        @Override
        public boolean retainAll(Book... elements) {
            return false;
        }

        @Override
        public void remove(int from, int to) {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Book> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Book book) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Book> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Book> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Book get(int index) {
            return null;
        }

        @Override
        public Book set(int index, Book element) {
            return null;
        }

        @Override
        public void add(int index, Book element) {

        }

        @Override
        public Book remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Book> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Book> listIterator(int index) {
            return null;
        }

        @Override
        public List<Book> subList(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public void addListener(InvalidationListener listener) {

        }

        @Override
        public void removeListener(InvalidationListener listener) {

        }
    };

    @FXML
    TableView booksList;
    @FXML
    TextField searchedItem;

    public void initialize(URL location, ResourceBundle resources) {
        find();
    }

    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void onActionBack (ActionEvent event){
        myController.setScreen("main");
    }

    @FXML
    private void find(){
        BookPersisting bookPersisting = new BookPersisting();

        data = booksList.getItems();
        data.clear();
        data.addAll(bookPersisting.getBooks(searchedItem.getText()));
    }
    @FXML
    public void keyListener(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER){
            find();
        }
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


    public static void setData(List<Book> data) {
        ScreenManageBooks.data.clear();
        ScreenManageBooks.data.addAll(data);
    }


    public void advancedSearch(ActionEvent event) {
        myController.setScreen("scene5");
    }
}
