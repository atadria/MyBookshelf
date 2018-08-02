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
        mainContainer.loadScreen("scene1","/screen1.fxml");
        mainContainer.loadScreen("scene2","/screen2.fxml");

        mainContainer.setScreen("main");
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


//        window = primaryStage;
//
//        Parent root = FXMLLoader.load(getClass().getResource("/mainscreen.fxml"));
//        primaryStage.setTitle("MyBookshelf");
//        primaryStage.setScene(new Scene(root, 300, 275));
//
//        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
