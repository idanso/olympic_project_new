package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene viewSummeryScene = new Scene(root);
        primaryStage.setTitle("olympic games program");
        primaryStage.setScene(viewSummeryScene);
        primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
}
