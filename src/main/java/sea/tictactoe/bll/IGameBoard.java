package sea.tictactoe.bll;

public interface IGameBoard {
    void changeGameboard(int row, int col);

    void setWinningLine(int[][] line);

    void setNextPlayer();

    boolean winCheck();

    boolean compareField(char a, char b, char c);

    boolean drawCheck();

    char getCurrentPlayer();

    int[][] getWinningLine();

    boolean isFieldOccupied(int row, int col);

    void resetGame();

    int getScoreX();

    int getScoreO();
}
