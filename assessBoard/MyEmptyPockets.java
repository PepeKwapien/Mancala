package assessBoard;

import gameItems.Board;

public class MyEmptyPockets implements IAssessBoard{
    private final static int POCKET_WEIGHT = 2;

    @Override
    public int assessBoard(int playersNumber, Board board) {
        int playersEmptyPockets = 0;
        for(int i = playersNumber*board.getNumOfPocketsForPlayer(); i < board.getNumOfPocketsForPlayer()*2; i++){
            if(board.getPocket(i).getStones() == 0){
                playersEmptyPockets += POCKET_WEIGHT;
            }
        }
        return (board.getStore(playersNumber).getStones() - board.getStore((playersNumber + 1)%2).getStones())
                + playersEmptyPockets;
    }
}
