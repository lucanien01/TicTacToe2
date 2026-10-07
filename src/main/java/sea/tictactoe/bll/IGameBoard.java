package sea.tictactoe.bll;

public interface IGameBoard {
    void changeGameboard(int row, int col);

    void setGamemode(String chooseMode);

    void setWinningLine(int[][] line);

    void setNextPlayer();

    boolean winCheck();

    boolean drawCheck();

    char getCurrentPlayer();

    int[][] getWinningLine();

    boolean isFieldOccupied(int row, int col);

    void resetGame();

    int getScoreX();

    int getScoreO();
}
