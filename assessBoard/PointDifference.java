package assessBoard;

import gameItems.Board;

public class PointDifference implements IAssessBoard{
    @Override
    public int assessBoard(int playersNumber, Board board) {
        return (board.getStore(playersNumber).getStones() - board.getStore((playersNumber + 1)%2).getStones());
    }
}
