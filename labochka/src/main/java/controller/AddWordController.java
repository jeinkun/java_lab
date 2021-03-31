package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Repository;

public class AddWordController {

    Repository data = new Repository();

    @FXML
    private Button aw_btn_add;

    @FXML
    private Button aw_btn_back;

    @FXML
    private TextField aw_tf_word;

    @FXML
    private TextArea aw_ta_word;

    @FXML
    void initialize() {

        aw_btn_back.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/MainView.fxml"));
            try {
                loader.load();

                aw_btn_back.getScene().getWindow().hide();

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Cловарь");
                stage.show();
            } catch (Exception e) {
                // e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ОШИБКА!");
                alert.setHeaderText("Ошибка при загрузке окна MainView.fxml");
                alert.setContentText(String.valueOf(e));

                alert.showAndWait();
            }
        });

        aw_btn_add.setOnAction(actionEvent -> {
            String res = data.setData(aw_tf_word.getText().toUpperCase(), aw_ta_word.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Отчет!");
            alert.setHeaderText(res);

            alert.showAndWait();

        });

    }
}
