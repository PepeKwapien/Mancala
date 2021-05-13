package players;

import gameItems.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandPlayer implements IPlayer{

    private final BufferedReader reader;
    private int numOfMoves;
    private long elapsedTime;

    public CommandPlayer(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        numOfMoves = 0;
        elapsedTime = 0;
    }

    @Override
    public int makeMove(int playersNumber, Board currentBoard) {
        numOfMoves++;
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> availablePockets = currentBoard.getAvailableMovesForPlayer(playersNumber);

        Integer move = null;

        if(playersNumber == 0){
            System.out.println("Player1:");
        }
        else{
            System.out.println("Player2:");
        }

        System.out.println("What will be your next move?");

        boolean correctMove = false;
        while(!correctMove){
            try {
                move = Integer.parseInt(reader.readLine());

                if(!availablePockets.contains(move)){
                    String listString = availablePockets.stream().map(Object::toString)
                            .collect(Collectors.joining(", "));
                    System.out.println("Available moves: " + listString);
                    System.out.println("Try again!");
                    continue;
                }
                correctMove = true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Type in number of a pocket!");
            }
        }
        elapsedTime += System.currentTimeMillis() - startTime;

        return move;
    }

    @Override
    public int getNumOfMovesMade() {
        return numOfMoves;
    }

    @Override
    public long getTimeElapsed() {
        return elapsedTime;
    }

    public void closeReader(){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
