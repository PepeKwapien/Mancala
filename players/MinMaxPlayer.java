package players;

import algorithm.MinMaxAlgorithm;
import gameItems.Board;

public class MinMaxPlayer implements IPlayer{

    private MinMaxAlgorithm minMaxAlgorithm;
    private final int maxDepth;

    public MinMaxPlayer(int maxDepth){
        minMaxAlgorithm = null;
        this.maxDepth = maxDepth;
    }

    @Override
    public int makeMove(int playersNumber, Board currentBoard) {
        if(minMaxAlgorithm == null){
            minMaxAlgorithm = new MinMaxAlgorithm(playersNumber, maxDepth);
        }
        int moveToMake = minMaxAlgorithm.makeMove(currentBoard);
        System.out.printf("Player%d chose pocket %d%n", playersNumber + 1, moveToMake);
        return moveToMake;
    }
}
