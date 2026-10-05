package sea.tictactoe.bll;

import javafx.event.ActionEvent;

import java.util.ArrayList;

public class GameBoard {

    // Instance variables
    private char currentPlayer = 'X';
    private String gamemode;
    private char[][] gameboard = {{' ', ' ', ' '},
                                  {' ', ' ', ' '},
                                  {' ', ' ', ' '}};

    public void setGamemode(String chooseMode) {
        this.gamemode = chooseMode;
    }

    public void changeGameboard(int row, int col){
        if(gameboard[row][col] == ' ') {
            gameboard[row][col] = currentPlayer;
            System.out.println("Pressed!");
            getNextPlayer();
            winCheck();
        }
    }

    public void getNextPlayer(){
        if(currentPlayer == 'X'){
            currentPlayer = 'O';
        } else{
            currentPlayer = 'X';
        }
    }

    public char winCheck(){

        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a){
                case 0:
                    line = "" + gameboard[0][0] + gameboard[0][1] + gameboard[0][2];
                    break;
                case 1:
                    line = "" + gameboard[1][0] + gameboard[1][1] + gameboard[1][2];
                    break;
                case 2:
                    line = "" + gameboard[2][0] + gameboard[2][1] + gameboard[2][2];
                    break;
                case 3:
                    line = "" + gameboard[0][0] + gameboard[1][0] + gameboard[2][0];
                    break;
                case 4:
                    line = "" + gameboard[0][1] + gameboard[1][1] + gameboard[2][1];
                    break;
                case 5:
                    line = "" + gameboard[0][2] + gameboard[1][2] + gameboard[2][2];
                    break;
                case 6:
                    line = "" + gameboard[0][0] + gameboard[1][1] + gameboard[2][2];
                    break;
                case 7:
                    line = "" + gameboard[0][2] + gameboard[1][1] + gameboard[2][0];
                    break;
            }

            if(line.equals("XXX")){
                return 'X';
            }
            if(line.equals("OOO")){
                return 'O';
            }
        }
        return ' ';
    }

    public char getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean isFieldOccupied(int row, int col) {
        if(this.gameboard[row][col] != ' ') {
            return true;
        }
        return false;
    }
}
