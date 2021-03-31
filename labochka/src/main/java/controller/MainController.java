package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button mv_btn_search;

    @FXML
    private Button mv_btn_add;

    @FXML
    private void opener(Button target, String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(String.format("../view/%s", window)));
        try {
            loader.load();

            target.getScene().getWindow().hide();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cловарь");

            String fontograd = "/sources/fonts/Ustroke Regular.ttf";
            Font.loadFont(getClass().getResourceAsStream(fontograd), 14);
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ОШИБКА!");
            alert.setHeaderText(String.format("Ошибка при загрузке окна %s", window));
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        mv_btn_search.setOnAction(actionEvent -> {
            opener(mv_btn_search, "SearchView.fxml");
        });
        mv_btn_add.setOnAction(actionEvent -> {
            opener(mv_btn_add, "AddWordView.fxml");
        });
    }
}
