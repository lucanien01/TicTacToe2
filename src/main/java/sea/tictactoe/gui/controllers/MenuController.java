package sea.tictactoe.gui.controllers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MenuController {

    public void initialize(){
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2));

    }

    public void openGameboard(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sea/tictactoe/views/GameView.fxml"));

        Parent root = loader.load();
        GameController mainController = loader.getController();
        mainController.getGameBoard().setGamemode(btn.getText());
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();

    }

}
