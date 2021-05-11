package players;

import gameItems.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandPlayer implements IPlayer{

    private final BufferedReader reader;

    public CommandPlayer(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public int makeMove(int playersNumber, Board currentBoard) {
        ArrayList<Integer> availablePockets = new ArrayList<>();

        int offset = playersNumber*currentBoard.getNumOfPocketsForPlayer();
        for(int i = offset;
            i < currentBoard.getNumOfPocketsForPlayer() + offset*playersNumber; i++){
            if(currentBoard.getPocket(i).getStones() != 0){
                availablePockets.add(i - offset + 1);
            }
        }

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

        return move;
    }

    public void closeReader(){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
