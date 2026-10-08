package sea.tictactoe.bll;

import java.util.Random;

public class GameBoard implements IGameBoard {

    // Instance variables
    private char currentPlayer = 'X';
    private char[][] gameboard = {{' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};
    private String gamemode;
    private int[][] winningLine;
    private int[] aiField;

    // Player Scores
    private int scoreX = 0;
    private int scoreO = 0;
    private char winner;

    // Handles AI controls
    private void aiMovement() {
        Random rand = new Random();

        int row = rand.nextInt(gameboard.length);
        int col = rand.nextInt(gameboard.length);

        // Legal move
        if (!isFieldOccupied(row, col)) {
            changeGameboard(row, col);
            setAiField(new int[]{row, col});
        } else { // Try again
            aiMovement();
        }
    }

    public void changeGameboard(int row, int col) {
        if (!isFieldOccupied(row, col)) {
            gameboard[row][col] = currentPlayer;

            if (winCheck()) {
                setWinner(currentPlayer);
            } else if (!drawCheck()) {
                setNextPlayer();
                if (gamemode.equals("Single-Player") && currentPlayer == 'O') {
                    aiMovement();
                }
            }
        }
    }

    // Checks if there is a win
    public boolean winCheck() {

        // Horizontal win check
        for (int i = 0; i < gameboard.length; i++) {
            if (compareField(gameboard[i][0], gameboard[i][1], gameboard[i][2])) {
                setWinningLine(new int[][]{{i, 0}, {i, 1}, {i, 2}});
                return true;
            }
        }
        // Vertical win check
        for (int i = 0; i < gameboard.length; i++) {
            if (compareField(gameboard[0][i], gameboard[1][i], gameboard[2][i])) {
                setWinningLine(new int[][]{{0, i}, {1, i}, {2, i}});
                return true;
            }
        }
        // Cross win check
        if (compareField(gameboard[0][0], gameboard[1][1], gameboard[2][2])) {
            setWinningLine(new int[][]{{0, 0}, {1, 1}, {2, 2}});
            return true;
        }
        if (compareField(gameboard[2][0], gameboard[1][1], gameboard[0][2])) {
            setWinningLine(new int[][]{{2, 0}, {1, 1}, {0, 2}});
            return true;
        }
        return false;
    }

    // Helper method for wincheck
    private boolean compareField(char a, char b, char c) {
        if (a != ' ' && a == b && b == c) {
            return true;
        }
        return false;
    }

    // Checks if the board is full
    public boolean drawCheck() {
        if (!winCheck()) {
            for (char[] chars : gameboard) {
                for (char aChar : chars) {
                    if (aChar == ' ') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Checks if the current field is occupied given a row and col
    public boolean isFieldOccupied(int row, int col) {
        if (this.gameboard[row][col] != ' ') {
            return true;
        }
        return false;
    }

    // Resets game
    public void resetGame() {
        for (int r = 0; r < gameboard.length; r++) {
            for (int c = 0; c < gameboard[r].length; c++) {
                gameboard[r][c] = ' ';

            }
        }
        currentPlayer = 'X';
        setWinningLine(new int[3][2]);
    }

    // Getters
    public int getScoreX() {
        return scoreX;
    }

    public int getScoreO() {
        return scoreO;
    }

    public char getWinner() {
        return winner;
    }

    public int[] getAiField() {
        return aiField;
    }

    public String getGamemode() {
        return gamemode;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public int[][] getWinningLine() {
        return winningLine;
    }

    // Setters
    public void setWinner(char winner) {
        this.winner = winner;
        if (this.winner == 'X') {
            scoreX++;
        } else if (this.winner == 'O') {
            scoreO++;
        }
    }

    public void setGamemode(String chooseMode) {
        this.gamemode = chooseMode;
    }

    public void setAiField(int[] aiField) {
        this.aiField = aiField;
    }

    public void setWinningLine(int[][] line) {
        this.winningLine = line;
    }

    public void setNextPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}
