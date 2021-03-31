package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Repository;

import java.util.ArrayList;

public class SearchController {

    Repository data = new Repository();

    @FXML
    private Button sv_btn_search;

    @FXML
    private Button sv_btn_back;

    @FXML
    private TextArea sv_ta_text;

    @FXML
    private TextField sv_tf_word;

    @FXML
    private CheckBox sv_chb_strong;

    @FXML
    void initialize() {

        sv_btn_back.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/MainView.fxml"));
            try {
                loader.load();
                sv_btn_back.getScene().getWindow().hide();
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

        sv_btn_search.setOnAction(actionEvent -> {
            sv_ta_text.clear();
            String text = sv_tf_word.getText().toUpperCase();
            ArrayList<String> results = data.getData(text, sv_chb_strong.isSelected());
            if (results.isEmpty()){
                sv_ta_text.setText("Ничего не найдено");
            } else {
                for (String result : results) {
                    sv_ta_text.appendText(result);
                }
            }
        });

    }
}
