package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

//    Stage window;
//    Scene mainscene, scene2, scene3;

    @Override
    public void start(Stage primaryStage) throws Exception{

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen("main","/mainscreen.fxml");
        mainContainer.loadScreen("scene1", "/screenFindBooks.fxml");
        mainContainer.loadScreen("scene2", "/screenManageBooks.fxml");
        mainContainer.loadScreen("scene3", "/screenEdit.fxml");
        mainContainer.loadScreen("scene4", "/screenAdd.fxml");
        mainContainer.loadScreen("scene5", "/screenAdvancedSearch.fxml");

        mainContainer.setScreen("main");
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
