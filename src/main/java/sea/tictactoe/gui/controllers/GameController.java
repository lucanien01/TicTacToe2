package sea.tictactoe.gui.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sea.tictactoe.bll.GameBoard;
import javafx.scene.control.Label;

public class GameController {

    // Instance variables
    private GameBoard gameBoard = new GameBoard();
    private Button[][] gameBtns = new Button[3][3];

    @FXML
    private GridPane grid;

    @FXML
    private Button btnNewGame;

    @FXML
    private Label lblPlayerX;

    @FXML
    private Label lblPlayerO;

    // Initializer
    public void initialize(){
        for (Node node : grid.getChildren()){
            Integer row = GridPane.getRowIndex(node);
            Integer col = GridPane.getColumnIndex(node);

            if(row == null) {row = 0;}
            if(col == null) {col = 0;}
            gameBtns[row][col] = (Button) node;
        }
    }

    // Gets indexes from grid & sets appropiate symbols on btns
    @FXML
    public void onButtonClickSetSymbol(ActionEvent actionEvent){
        Node buttonClicked = (Node) actionEvent.getSource();
        int[] btnCoords = getRowAndCol((Button) buttonClicked);

        // check if the button is not already clicked
        if (!gameBoard.isFieldOccupied(btnCoords[0], btnCoords[1])){
            setBtnVisuals((Button) buttonClicked, gameBoard.getCurrentPlayer());
            gameBoard.changeGameboard(btnCoords[0], btnCoords[1]);
            checkForWinOrDraw();
        }

        if (gameBoard.getGamemode().equals("Single-Player")) {
            Button aiBtn = gameBtns[gameBoard.getAiField()[0]][gameBoard.getAiField()[1]];
            setBtnVisuals(aiBtn, 'O');
        }
    }

    // Takes in button and char as parameters and set visual
    private void setBtnVisuals(Button btn, char symbol){
        btn.setText(symbol + "");
        btn.getStyleClass().add(symbol == 'X' ? "game-btn-x" : "game-btn-o");
    }

    // Returns buttons row and col
    private int[] getRowAndCol(Button btn){
        for (int row = 0; row < gameBtns.length; row ++){
            for (int col = 0; col < gameBtns[row].length; col++){
                if (gameBtns[row][col] == btn){
                    return new int[]{row, col};
                }
            }
        }
        return new int [2];
    }

    // Handles all ui displayed as the game stops.
    private void checkForWinOrDraw() {
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

    // Highlights the 3 buttons that is the winning line
    private void highlightWinningLine(){
        for (int[] i: gameBoard.getWinningLine()){
            int row = i[0];
            int col = i[1];

            gameBtns[row][col].getStyleClass().add(gameBoard.getWinner() == 'X' ? "winning-line-x" : "winning-line-o");
        }
    }

    // Disables all game-buttons as the game sotps
    private void disableButtons(){
        for (Node node : grid.getChildren()) {
            Button btn = (Button) node;
            btn.setDisable(true);
        }
    }

    // Resets game and handles .css
    public void resetGame(){
        for (Node node : grid.getChildren()){
            Button btn = (Button) node;
            btn.setDisable(false);
            btn.setText("");
            btn.getStyleClass().removeIf(element -> element.contains("winning-line-"));
            btn.getStyleClass().removeIf(element -> element.contains("game-btn-"));
        }
        gameBoard.resetGame();
    }

    public GameBoard getGameBoard(){
        return gameBoard;
    }
}
