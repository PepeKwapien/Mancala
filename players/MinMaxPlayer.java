package players;

import algorithm.MinMaxAlgorithm;
import assessBoard.IAssessBoard;
import gameItems.Board;

public class MinMaxPlayer implements IPlayer{

    private final MinMaxAlgorithm minMaxAlgorithm;
    private final boolean printMsg;
    private int numOfMoves;
    private long elapsedTime;

    public MinMaxPlayer(int maxDepth, boolean isAlphaBeta, boolean printMsg, IAssessBoard iAssessBoard){
        minMaxAlgorithm = new MinMaxAlgorithm(maxDepth, isAlphaBeta, iAssessBoard);
        this.printMsg = printMsg;
        numOfMoves = 0;
        elapsedTime = 0;
    }

    @Override
    public int makeMove(int playersNumber, Board currentBoard) {
        numOfMoves++;
        long startTime = System.nanoTime();
        int moveToMake = minMaxAlgorithm.makeMove(playersNumber, currentBoard);
        elapsedTime += System.nanoTime() - startTime;

        if(printMsg){
            System.out.printf("Player%d chose pocket %d%n", playersNumber + 1, moveToMake);
        }

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

    public int getNumOfNodesVisitedTotal(){
        return minMaxAlgorithm.getNodesVisitedTotal();
    }
}
