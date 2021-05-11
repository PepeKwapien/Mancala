package gameItems;

import java.util.ArrayList;

public class Board {

    private final Store[] stores;
    private final ArrayList<Pocket> pockets;
    private final int numOfStones;
    /* Game's perspective
    PlayerA 1   2   3   4   5   6
            6   5   4   3   2   1  PlayerB

        Programmer's perspective
    PlayerA 0   1   2   3   4   5
            11  10  9   8   7   6  PlayerB
     */

    public Board(int numOfPockets, int numOfStones){
        if(numOfPockets <= 0 || numOfStones <= 0){
            String msg = String.format("Board cannot have less than 0 pockets/stones. Given parameters: " +
                    "%d pockets and %d stones", numOfPockets, numOfStones);
            throw new IllegalArgumentException(msg);
        }

        pockets = new ArrayList<>();
        for(int i = 0; i < 2*numOfPockets; i++){
            pockets.add(new Pocket(numOfStones));
        }
        stores = new Store[2];
        for(int i = 0; i < 2; i++){
            stores[i] = new Store();
        }

        this.numOfStones = numOfStones;
    }

    public Store getStore(int i){
        if(i > 1 || i < 0){
            String msg = String.format("Store number should be either 0 or 1. Given number: %d", i);
            throw new IllegalArgumentException(msg);
        }

        return stores[i];
    }

    public Pocket getPocket(int i){
        if(i >= pockets.size() || i < 0){
            String msg = String.format("Pocket number should be between 0 and %d. Given number %d",
                    pockets.size() - 1, i);
            throw new IllegalArgumentException(msg);
        }

        return pockets.get(i);
    }

    public boolean isRowEmpty(int i){
        if(i > 1 || i < 0){
            String msg = String.format("Player number should be either 0 or 1. Given number: %d", i);
            throw new IllegalArgumentException(msg);
        }

        boolean hasNonEmptyPockets = false;

        for(int j = i*pockets.size()/2; j < (i + 1)*pockets.size()/2; j++){
            if(pockets.get(j).getStones() > 0){
                hasNonEmptyPockets = true;
                break;
            }
        }

        return hasNonEmptyPockets;
    }

    public int getNumOfPocketsForPlayer(){
        return pockets.size()/2;
    }

    public void finishGame(int playersNumber){
        int stonesFromBoard = 0;
        for(int i = 0; i < getNumOfPocketsForPlayer()*2; i++){
            stonesFromBoard += getPocket(i).flush();
        }

        getStore((playersNumber + 1)%2).addStones(stonesFromBoard);
    }

    public boolean makeMove(int playersNumber, int pocketIndex){
        int stones = getPocket(pocketIndex).flush();
        boolean hasScoredLastIteration = false;
        boolean canMoveAgain = false;

        //reassigning stones
        for(int i = stones; i > 0; i--){
            pocketIndex--;
            pocketIndex = pocketIndex < 0 ? getNumOfPocketsForPlayer()*2 - 1 : pocketIndex;

            //adding points (player2 scores)
            if(pocketIndex == getNumOfPocketsForPlayer() - 1 && playersNumber == 1
                    && !hasScoredLastIteration){
                getStore(1).addStones(1);
                hasScoredLastIteration = true;
                if(i == 1){
                    canMoveAgain = true;
                }
                else{
                    ++pocketIndex;
                }
            }
            //adding points (player1 scores)
            else if(pocketIndex == getNumOfPocketsForPlayer()*2 - 1 && playersNumber == 0
                    && !hasScoredLastIteration){
                getStore(0).addStones(1);
                hasScoredLastIteration = true;
                if(i == 1){
                    canMoveAgain = true;
                }
                else{
                    ++pocketIndex;
                }
            }
            else{
                //adding stones to consecutive pockets
                getPocket(pocketIndex).addStone();
                hasScoredLastIteration = false;
                //if pocket was empty and this is the last stone to assign clear stones across the board
                if(getPocket(pocketIndex).getStones() == 1
                        && i == 1
                        && getNumOfPocketsForPlayer()*playersNumber <= pocketIndex
                        && pocketIndex < (playersNumber + 1)*getNumOfPocketsForPlayer()){
                    getStore(playersNumber)
                            .addStones(getPocket(getNumOfPocketsForPlayer()*2 -1 - pocketIndex)
                                    .flush());

                    getStore(playersNumber).addStones(getPocket(pocketIndex).flush());
                }
            }
        }

        return canMoveAgain;
    }

    @Override
    public String toString(){
        StringBuilder board = new StringBuilder();

        for(int i = 0; i < 14; i++){
            board.append("-----");
        }
        board.append("\n\t\t\t|\t");
        for(int i = 0; i < getNumOfPocketsForPlayer(); i++){
            board.append(String.format("%4d  ", i + 1));
        }
        board.append("\t|\n");
        board.append("Player1:\t|\t");

        for(int i = 0; i < getNumOfPocketsForPlayer(); i++){
            board.append(String.format("( %2d )", pockets.get(i).getStones()));
        }

        board.append(String.format("\t|\t[ %2d ]\n\t[ %2d ]\t|\t", stores[1].getStones(), stores[0].getStones()));

        for(int i = getNumOfPocketsForPlayer()*2 - 1; i >= getNumOfPocketsForPlayer(); i--){
            board.append(String.format("( %2d )", pockets.get(i).getStones()));
        }

        board.append("\t|\t:Player2\n");
        board.append("\t\t\t|\t");
        for(int i = getNumOfPocketsForPlayer(); i > 0; i--){
            board.append(String.format("%4d  ", i));
        }
        board.append("\t|\n");
        for(int i = 0; i < 14; i++){
            board.append("-----");
        }

        return board.toString();
    }

    @Override
    public Board clone(){
        Board clonedBoard = new Board(getNumOfPocketsForPlayer(), this.numOfStones);
        clonedBoard.stores[0].setStones(stores[0].getStones());
        clonedBoard.stores[1].setStones(stores[1].getStones());

        for(int i = 0; i < pockets.size(); i++){
            clonedBoard.pockets.get(i).setStones(pockets.get(i).getStones());
        }

        return clonedBoard;
    }
}
