package players;

import algorithm.MinMaxAlgorithm;
import gameItems.Board;

public class MinMaxPlayer implements IPlayer{

    private final MinMaxAlgorithm minMaxAlgorithm;

    public MinMaxPlayer(int maxDepth){
        minMaxAlgorithm = new MinMaxAlgorithm(maxDepth);
    }

    @Override
    public int makeMove(int playersNumber, Board currentBoard) {
        int moveToMake = minMaxAlgorithm.makeMove(playersNumber, currentBoard);
        System.out.printf("Player%d chose pocket %d%n", playersNumber + 1, moveToMake);
        return moveToMake;
    }
}
