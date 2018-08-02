package gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Screen2 implements Initializable, ControlledScreen {

    ScreensController myController;

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
        ObservableList<Book> data = booksList.getItems();
        data.clear();
        data.add(new Book("new", " ", " "));
    }
}
