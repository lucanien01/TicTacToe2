package sea.tictactoe.gui.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sea.tictactoe.bll.GameBoard;

import java.awt.*;

public class MainController {

    private GameBoard gameBoard = new GameBoard();

    @FXML
    public void onButtonClickSetSymbol(ActionEvent actionEvent){

        Node buttonClicked = (Node) actionEvent.getSource();

        Integer row = GridPane.getRowIndex(buttonClicked);
        Integer col = GridPane.getColumnIndex(buttonClicked);

        if(row == null) {row = 0;}
        if(col == null) {col = 0;}

        Button btn = (Button) buttonClicked;

        if (!gameBoard.isFieldOccupied(row, col)){
            btn.setText(gameBoard.getCurrentPlayer() + "");
        }
        gameBoard.changeGameboard(row, col);

    }
}
