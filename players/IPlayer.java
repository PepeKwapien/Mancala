package players;

import gameItems.Board;

public interface IPlayer {
    /**
     *
     * @param playersNumber number of a player making move (0-1)
     * @param currentBoard reference to a current state of the board
     * @return index of chosen pocket (1 - number of pockets for one player)
     */
    int makeMove(int playersNumber, Board currentBoard);
    int getNumOfMovesMade();
    long getTimeElapsed();
}
