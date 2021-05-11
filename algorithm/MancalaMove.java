package algorithm;

import gameItems.Board;

import java.util.ArrayList;

class MancalaMove {
    private int previousPocket;
    private ArrayList<MancalaMove> children;
    private Integer score;

    private MancalaMove(){}

    private MancalaMove(int playersNumber, int currentPlayersNumber,
                        int previousPocket, Board board, int reimainingDepth){

        this.previousPocket = previousPocket;
        children = new ArrayList<>();

        if(reimainingDepth != 1){
            children.add(new MancalaMove(playersNumber,(currentPlayersNumber + 1) % 2,
                    0, board.clone(), reimainingDepth - 1));
            for(int i = 1; i <= board.getNumOfPocketsForPlayer(); i++){
                if(board.getPocket(i + currentPlayersNumber*board.getNumOfPocketsForPlayer() - 1).getStones() != 0){
                    Board clonedBoard = board.clone();
                    clonedBoard.makeMove(currentPlayersNumber,
                            i + currentPlayersNumber*board.getNumOfPocketsForPlayer() - 1);
                    children.add(new MancalaMove(playersNumber, (currentPlayersNumber + 1)%2,
                            i, clonedBoard, reimainingDepth - 1));
                }
            }

            if(children.isEmpty()){
                score = assessBoard(playersNumber, board);
            }
            else{
                score = null;
            }
        }
        else{
            score = assessBoard(playersNumber, board);
        }
    }

    public static MancalaMove generateMancalaTree(int playersNumber, int currentPlayersNumber,
                                                  int previousPocket, Board board, int maxDepth){
        return new MancalaMove(playersNumber, currentPlayersNumber, previousPocket, board, maxDepth);
    }

    private int assessBoard(int playersNumber, Board board){
        return (board.getStore(playersNumber).getStones() - board.getStore((playersNumber + 1)%2).getStones());
    }

    public int getPreviousPocket() {
        return previousPocket;
    }

    public ArrayList<MancalaMove> getChildren() {
        return children;
    }

    public Integer getScore() {
        return score;
    }
}
