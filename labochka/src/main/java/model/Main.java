package model;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
        primaryStage.setTitle("Словарь");
        primaryStage.setScene(new Scene(root, 498, 422));
        primaryStage.show();

        String fontograd = "/sources/fonts/Ustroke Regular.ttf";
        Font.loadFont(getClass().getResourceAsStream(fontograd), 14);
    }

}
