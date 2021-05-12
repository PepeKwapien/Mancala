package algorithm;

import gameItems.Board;

import java.util.ArrayList;

class MancalaMove {
    private int previousPocket;
    private ArrayList<MancalaMove> children;
    private Integer score;

    private MancalaMove(){}

    private MancalaMove(int playersNumber, int currentPlayersNumber,
                        int previousPocket, Board board, int reimainingDepth, boolean repeatMove){

        this.previousPocket = previousPocket;
        children = new ArrayList<>();

        if(reimainingDepth != 1){
            if(repeatMove){
                children.add(new MancalaMove(playersNumber,(currentPlayersNumber + 1) % 2,
                        previousPocket, board.clone(), reimainingDepth - 1, false));
            }
            else{
                for(int i = 1; i <= board.getNumOfPocketsForPlayer(); i++){
                    if(board.getPocket(i + currentPlayersNumber*board.getNumOfPocketsForPlayer() - 1).getStones() != 0){
                        Board clonedBoard = board.clone();
                        boolean dontSwithPlayer = clonedBoard.makeMove(currentPlayersNumber,
                                i + currentPlayersNumber*board.getNumOfPocketsForPlayer() - 1);
                        children.add(new MancalaMove(playersNumber, (currentPlayersNumber + 1)%2,
                                i, clonedBoard, reimainingDepth - (dontSwithPlayer ? 0 : 1), dontSwithPlayer));
                    }
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
        return new MancalaMove(playersNumber, currentPlayersNumber, previousPocket, board, maxDepth, false);
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
