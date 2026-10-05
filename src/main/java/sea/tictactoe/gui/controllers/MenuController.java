package sea.tictactoe.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {


    @FXML
    private Button btnSingleplayer;

    @FXML
    private Button btnMultiplayer;

    public void openGameboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sea/tictactoe/views/MainView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();

    }

}
