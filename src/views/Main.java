package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Begin.fxml"));
        primaryStage.setTitle("Tetris");
        Scene scene = new Scene(root);
        scene.getRoot().requestFocus();
       // scene.getStylesheets().add("css/main.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();     
        
    }
}
