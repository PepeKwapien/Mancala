package algorithm;

import gameItems.Board;

public class MinMaxAlgorithm {

    int maxDepth;
    int playersNumber;

    public MinMaxAlgorithm(int playersNumber, int maxDepth){
        this.maxDepth = maxDepth;
        this.playersNumber = playersNumber;
    }

    public int makeMove(Board currentBoard){
        return Max(MancalaMove.generateMancalaTree(playersNumber, playersNumber,
                0, currentBoard, maxDepth), true)[0];
    }

    private int[] Max(MancalaMove root, boolean isInitial){

        Integer moveToMake = null;
        int score = 0;

        if(!root.getChildren().isEmpty()){
            for (MancalaMove mancalaMove : root.getChildren()) {
                    int[] currentResult = Min(mancalaMove);

                    if(moveToMake == null || currentResult[1] > score) {
                        moveToMake = currentResult[0];
                        score = currentResult[1];
                    }
            }
        }
        else{
            moveToMake = root.getPreviousPocket();
            score = root.getScore();
        }

        if(!isInitial){
            moveToMake = root.getPreviousPocket();
        }

        return new int[]{moveToMake, score};
    }

    private int[] Min(MancalaMove root){
        Integer moveToMake = null;
        int score = 0;

        if(!root.getChildren().isEmpty()){
            for (MancalaMove mancalaMove : root.getChildren()) {
                if(mancalaMove.getPreviousPocket() != 0){
                    int[] currentResult = Max(mancalaMove, false);

                    if (moveToMake == null || currentResult[1] < score) {
                        moveToMake = currentResult[0];
                        score = currentResult[1];
                    }
                }
            }
        }
        else{
            score = root.getScore();
        }

        return new int[]{root.getPreviousPocket(), score};
    }
}
