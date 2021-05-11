package players;

import gameItems.Board;

public interface IPlayer {
    int makeMove(int playersNumber, Board currentBoard);
}
