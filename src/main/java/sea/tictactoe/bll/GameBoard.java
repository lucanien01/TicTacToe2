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
    private int[][] winningLine;

    // Player Scores
    private int scoreX = 0;
    private int scoreO = 0;

    public void setGamemode(String chooseMode) {
        this.gamemode = chooseMode;
    }

    public void changeGameboard(int row, int col){
        if(gameboard[row][col] == ' ') {
            gameboard[row][col] = currentPlayer;
            System.out.println("Pressed!");
            if(!winCheck()){
                drawCheck();
            } else {
                if(currentPlayer == 'X'){
                    scoreX++;
                }else if(currentPlayer == 'O'){
                    scoreO++;
                }
            }
            getNextPlayer();
        }
    }

    public void setWinningLine(int[][] line){
        this.winningLine = line;
    }

    public void getNextPlayer(){
        if(currentPlayer == 'X'){
            currentPlayer = 'O';
        } else{
            currentPlayer = 'X';
        }
    }

    public boolean winCheck(){

        // Horizontal win check
        for (int i = 0; i < gameboard.length; i++) {
            if(compareField(gameboard[i][0], gameboard[i][1], gameboard[i][2])){
                setWinningLine(new int[][]{{i,0},{i,1},{i,2}});
                return true;
            }
        }

        // Vertical win check
        for (int i = 0; i < gameboard.length; i++) {
            if(compareField(gameboard[0][i], gameboard[1][i], gameboard[2][i])) {
                setWinningLine(new int[][]{{0,i},{1,i},{2,i}});
                return true;
            }
        }

        // Cross win check
        if (compareField(gameboard[0][0], gameboard[1][1], gameboard[2][2]))
        {
            setWinningLine(new int[][]{{0,0},{1,1},{2,2}});
            return true;
        }
        if (compareField(gameboard[2][0], gameboard[1][1], gameboard[0][2]))
        {
            setWinningLine(new int[][]{{2,0},{1,1},{0,2}});
            return true;
        }
        return false;
    }

    public boolean compareField(char a, char b, char c){
        if(a != ' ' && a == b && b == c){
            return true;
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

    public int[][] getWinningLine(){
        return winningLine;
    }

    public boolean isFieldOccupied(int row, int col) {
        if(this.gameboard[row][col] != ' ') {
            return true;
        }
        return false;
    }

    public void resetGame(){
        for(int r = 0; r < gameboard.length; r++){
            for (int c = 0; c < gameboard[r].length; c++){
                gameboard[r][c] = ' ';

            }
        }
        currentPlayer = 'X';
        setWinningLine(new int[3][2]);
    }

    // Getters
    public int getScoreX(){
        return scoreX;
    }

    public int getScoreO(){
        return scoreO;
    }

}
