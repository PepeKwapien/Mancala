package algorithm;

import gameItems.Board;

import java.util.ArrayList;

public class MinMaxAlgorithm {

    private final int maxDepth;

    public MinMaxAlgorithm(int maxDepth){
        this.maxDepth = maxDepth;
    }

    public int makeMove(int playersNumber, Board currentBoard){
        return Max(playersNumber, playersNumber, 0, currentBoard, maxDepth, false, true)[0];
    }

    private int[] Max(int playersNumber, int currentPlayersNumber,
                      int previousPocket, Board board, int remainingDepth, boolean repeatMove, boolean isInitial){
        ArrayList<Integer> availablePockets = board.getAvailableMovesForPlayer(currentPlayersNumber);
        Integer bestPath = null;
        int score = 0;

        if(repeatMove){
            return Min(playersNumber, (currentPlayersNumber + 1)%2, previousPocket, board,
                    remainingDepth - 1, false);
        }
        else{
            if(availablePockets.size() == 0 || remainingDepth == 1){
                bestPath = previousPocket;
                score = assessBoard(playersNumber, board);
            }
            else{
                for(int i : availablePockets){
                    Board clonedBoard = board.clone();
                    boolean dontSwitchPlayer = clonedBoard.makeMove(currentPlayersNumber,
                            i + currentPlayersNumber*board.getNumOfPocketsForPlayer() - 1);
                    int[] childScore = Min(playersNumber, (currentPlayersNumber + 1)%2, i,
                            clonedBoard, remainingDepth - (dontSwitchPlayer ? 0 : 1), dontSwitchPlayer);

                    if(bestPath == null || childScore[1] > score){
                        bestPath = childScore[0];
                        score = childScore[1];
                    }
                }
            }
        }

        if(!isInitial){
            bestPath = previousPocket;
        }

        return new int[]{bestPath, score};
    }

    private int[] Min(int playersNumber, int currentPlayersNumber,
                      int previousPocket, Board board, int remainingDepth, boolean repeatMove){

        ArrayList<Integer> availablePockets = board.getAvailableMovesForPlayer(currentPlayersNumber);
        Integer bestPath = null;
        int score = 0;

        if(repeatMove){
            return Max(playersNumber, (currentPlayersNumber + 1)%2, previousPocket, board,
                    remainingDepth - 1, false, false);
        }
        else{
            if(availablePockets.size() == 0 || remainingDepth == 1){
                score = assessBoard(playersNumber, board);
            }
            else{
                for(int i : availablePockets){
                    Board clonedBoard = board.clone();
                    boolean dontSwitchPlayer = clonedBoard.makeMove(currentPlayersNumber,
                            i + currentPlayersNumber*board.getNumOfPocketsForPlayer() - 1);
                    int[] childScore = Max(playersNumber, (currentPlayersNumber + 1)%2, i, clonedBoard,
                            remainingDepth - (dontSwitchPlayer ? 0 : 1), dontSwitchPlayer, false);

                    if(bestPath == null || childScore[1] < score){
                        bestPath = childScore[0];
                        score = childScore[1];
                    }
                }
            }
        }

        bestPath = previousPocket;

        return new int[]{bestPath, score};
    }

    private int assessBoard(int playersNumber, Board board){
        return (board.getStore(playersNumber).getStones() - board.getStore((playersNumber + 1)%2).getStones());
    }
}
