package sea.tictactoe.bll;

import javafx.event.ActionEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameBoard {

    // Instance variables
    private char currentPlayer = 'X';
    private char[][] gameboard = {{' ', ' ', ' '},
                                  {' ', ' ', ' '},
                                  {' ', ' ', ' '}};
    private String gamemode;

    public void setGamemode(String chooseMode) {
        this.gamemode = chooseMode;
    }

    public void changeGameboard(int row, int col){
        if(gameboard[row][col] == ' ') {
            gameboard[row][col] = currentPlayer;
            System.out.println("Pressed!");
            if(!winCheck()){
                drawCheck();
            }
            getNextPlayer();
        }
    }

    public void getNextPlayer(){
        if(currentPlayer == 'X'){
            currentPlayer = 'O';
        } else{
            currentPlayer = 'X';
        }
    }

    public boolean winCheck(){

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

            if(line.equals("XXX")) {
                System.out.println("X Wins");
            }
            if(line.equals("OOO")) {
                System.out.println("O Wins");
            }
            if(line.equals("XXX") || line.equals("OOO")){
                return true;
            }
        }
        return false;
    }

    public boolean drawCheck(){
        for (int r = 0; r < gameboard.length; r++){
            for (int c = 0; c <gameboard[r].length; c++){
                if (gameboard[r][c] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("Draw");
        return true;
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
