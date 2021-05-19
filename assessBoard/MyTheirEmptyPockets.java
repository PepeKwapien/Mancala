package assessBoard;

import gameItems.Board;

public class MyTheirEmptyPockets implements IAssessBoard{
    private final static int MY_POCKET_WEIGHT = 2;
    private final static int THEIR_POCKET_WEIGTH = 3;

    @Override
    public int assessBoard(int playersNumber, Board board) {
        int emptyPockets = 0;
        for(int i = 0; i < board.getNumOfPocketsForPlayer()*2; i++){
            if(board.getPocket(i).getStones() == 0){
                if(i >= playersNumber*board.getNumOfPocketsForPlayer()
                        && i < board.getNumOfPocketsForPlayer()*(playersNumber + 1)){
                    emptyPockets += MY_POCKET_WEIGHT;
                }
                else{
                    emptyPockets -= THEIR_POCKET_WEIGTH;
                }
            }
        }

        return (board.getStore(playersNumber).getStones() - board.getStore((playersNumber + 1)%2).getStones())
                + emptyPockets;
    }
}
