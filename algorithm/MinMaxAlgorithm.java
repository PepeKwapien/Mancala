package algorithm;

import gameItems.Board;

import java.util.ArrayList;

public class MinMaxAlgorithm {

    private final int maxDepth;
    private final boolean alphaBetaPruning;
    private final int DEFAULT_MAX_DEPTH = 4;
    private int nodesVisited;

    /*
    DEPTH EXPLAINED:
            (*)         - LEVEL 1
         (*)   (*)      - LEVEL 2
       (*)  (*)   (*)   - LEVEL 3
     */

    public MinMaxAlgorithm(int maxDepth, boolean alphaBetaPruning){
        this.maxDepth = maxDepth > 1 ? maxDepth : DEFAULT_MAX_DEPTH;
        this.alphaBetaPruning = alphaBetaPruning;
    }

    public int makeMove(int playersNumber, Board currentBoard){
        nodesVisited = 0;
        return Max(playersNumber, playersNumber, 0, currentBoard, maxDepth, false,
                true, null, null)[0];
    }

    private int assessBoard(int playersNumber, Board board){
        return (board.getStore(playersNumber).getStones() - board.getStore((playersNumber + 1)%2).getStones());
    }

    /**
     * Max returns tuple (array) containing best move path with highest score value and the mentioned score value.
     * Score value is used only in recursive calls to assess the best path.
     *
     * @param playersNumber indicates which player analyzes the board
     * @param currentPlayersNumber indicates which player's turn is being analyzed
     * @param previousPocket number of a pocket that was chosen and led to this state of the game
     * @param board reference to a current state of a board
     * @param remainingDepth how many more nodes should be analyzed
     * @param repeatMove indicates if this current node is a 'fake move'. algorithm inserts fake moves when
     *                   one player repeats its move
     * @param isInitial indicates if currently analyzed node is initial node in a tree. if yes it will return path
     *                  to its best child. if no it will return it's previousPocket argument
     * @param alpha it's parameter used only when alpha-pruning was enabled. it's considered to be the best alternative
     *              for max value
     * @param beta it's parameter used only when alpha-pruning was enabled. it's considered to be the best alternative
     *             for min value
     * @return array containing best pocket to chose [0] and best score you can achieve going down this path [1]
     */
    private int[] Max(int playersNumber, int currentPlayersNumber, int previousPocket, Board board,
                      int remainingDepth, boolean repeatMove, boolean isInitial, Integer alpha, Integer beta){
        nodesVisited++;
        ArrayList<Integer> availablePockets = board.getAvailableMovesForPlayer(currentPlayersNumber);
        Integer bestPath = null;
        int score = 0;

        // if yes then this node is a 'fake move' - it means it never takes place. other player moves again
        if(repeatMove){
            return Min(playersNumber, (currentPlayersNumber + 1)%2, previousPocket, board,
                    remainingDepth - 1, false, alpha, beta);
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

                    // if player can move again don't decrease remaining depth
                    int[] childScore = Min(playersNumber, (currentPlayersNumber + 1)%2, i,
                            clonedBoard, remainingDepth - (dontSwitchPlayer ? 0 : 1), dontSwitchPlayer,
                            alpha, beta);

                    if(bestPath == null || childScore[1] > score){
                        bestPath = childScore[0];
                        score = childScore[1];
                    }
                    if(alphaBetaPruning && beta != null && childScore[1] >= beta){
                        break;
                    }
                    if(alphaBetaPruning && (alpha == null || childScore[1] > alpha)){
                        alpha = childScore[1];
                    }
                }
            }
        }

        //if it's not top node return path to itself not to its best child
        if(!isInitial){
            bestPath = previousPocket;
        }

        return new int[]{bestPath, score};
    }

    /**
     * Min returns tuple (array) containing best move path with lowest score value and the mentioned score value.
     * Score value is used only in recursive calls to assess the best path.
     *
     * @param playersNumber indicates which player analyzes the board
     * @param currentPlayersNumber indicates which player's turn is being analyzed
     * @param previousPocket number of a pocket that was chosen and led to this state of the game
     * @param board reference to a current state of a board
     * @param remainingDepth how many more nodes should be analyzed
     * @param repeatMove indicates if this current node is a 'fake move'. algorithm inserts fake moves when
     *                   one player repeats its move
     * @param alpha it's parameter used only when alpha-pruning was enabled. it's considered to be the best alternative
     *              for max value in the current path
     * @param beta it's parameter used only when alpha-pruning was enabled. it's considered to be the best alternative
     *             for min value in the current path
     * @return array containing best pocket to chose [0] and best score you can achieve going down this path [1]
     */
    private int[] Min(int playersNumber, int currentPlayersNumber, int previousPocket, Board board,
                      int remainingDepth, boolean repeatMove, Integer alpha, Integer beta){
        nodesVisited++;
        ArrayList<Integer> availablePockets = board.getAvailableMovesForPlayer(currentPlayersNumber);
        Integer bestPath = null;
        int score = 0;

        if(repeatMove){
            return Max(playersNumber, (currentPlayersNumber + 1)%2, previousPocket, board,
                    remainingDepth - 1, false, false, alpha, beta);
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
                            remainingDepth - (dontSwitchPlayer ? 0 : 1), dontSwitchPlayer, false,
                            alpha, beta);

                    if(bestPath == null || childScore[1] < score){
                        bestPath = childScore[0];
                        score = childScore[1];
                    }
                    if(alphaBetaPruning && alpha != null && childScore[1] <= alpha){
                        break;
                    }
                    if(alphaBetaPruning && (beta == null || childScore[1] < beta)){
                        beta = childScore[1];
                    }
                }
            }
        }

        bestPath = previousPocket;

        return new int[]{bestPath, score};
    }
}
