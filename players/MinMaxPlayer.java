package players;

import algorithm.MinMaxAlgorithm;
import gameItems.Board;

public class MinMaxPlayer implements IPlayer{

    private final MinMaxAlgorithm minMaxAlgorithm;
    private int numOfMoves;
    private long elapsedTime;

    public MinMaxPlayer(int maxDepth, boolean isAlphaBeta){
        minMaxAlgorithm = new MinMaxAlgorithm(maxDepth, isAlphaBeta);
        numOfMoves = 0;
        elapsedTime = 0;
    }

    @Override
    public int makeMove(int playersNumber, Board currentBoard) {
        numOfMoves++;
        long startTime = System.currentTimeMillis();
        int moveToMake = minMaxAlgorithm.makeMove(playersNumber, currentBoard);
        elapsedTime += System.currentTimeMillis() - startTime;
        System.out.printf("Player%d chose pocket %d%n", playersNumber + 1, moveToMake);
        return moveToMake;
    }

    @Override
    public int getNumOfMovesMade() {
        return numOfMoves;
    }

    @Override
    public long getTimeElapsed() {
        return elapsedTime;
    }

    public int getNumOfNodesVisited(){
        return minMaxAlgorithm.getNodesVisited();
    }
}
