package sea.tictactoe.gui.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sea.tictactoe.bll.GameBoard;
import javafx.scene.control.Label;

import java.awt.*;

public class MainController {
    @FXML
    private GridPane grid;
    private GameBoard gameBoard = new GameBoard();
    private Button[][] gameBtns = new Button[3][3];

    @FXML
    private Button btnNewGame;

    @FXML
    private Label lblPlayerX;

    @FXML
    private Label lblPlayerO;

    public void initialize(){
        for (Node node : grid.getChildren()){
            Integer row = GridPane.getRowIndex(node);
            Integer col = GridPane.getColumnIndex(node);

            if(row == null) {row = 0;}
            if(col == null) {col = 0;}
            gameBtns[row][col] = (Button) node;
        }
    }

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
        gameOverUI();
    }

    public void gameOverUI() {
        if(gameBoard.winCheck()){
            highlightWinningLine();
            disableButtons();
            btnNewGame.setDisable(false);

            lblPlayerX.setText("X points: " + gameBoard.getScoreX());
            lblPlayerO.setText("O points: " + gameBoard.getScoreO());

        } else if(gameBoard.drawCheck()) {
            disableButtons();
            btnNewGame.setDisable(false);
        }
    }

    public void highlightWinningLine(){
        for (int[] i: gameBoard.getWinningLine()){
            int row = i[0];
            int col = i[1];
            gameBtns[row][col].getStyleClass().add("winning-btn");
        }
    }

    public void disableButtons(){
        for (Node node : grid.getChildren()) {
            Button btn = (Button) node;
            btn.setDisable(true);
        }
    }

    public void resetGame(){
        for (Node node : grid.getChildren()){

            Button btn = (Button) node;
            btn.setDisable(false);
            btn.setText("");

            btn.getStyleClass().remove("winning-btn");
        }
        gameBoard.resetGame();
    }
}
