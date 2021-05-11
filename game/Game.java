package game;

import gameItems.Board;
import players.IPlayer;
import players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
    private final Board board;
    private final IPlayer[] players;
    private int currentPlayer;
    private final int firstPlayer;
    private final boolean isFirstMoveRandom;
    private int numOfMoves;

    public Game(IPlayer player1, IPlayer player2, int numOfPockets, int numOfStones, boolean isFirstMoveRandom){
        board = new Board(numOfPockets, numOfStones);
        players = new IPlayer[2];
        players[0] = player1;
        players[1] = player2;
        this.isFirstMoveRandom = isFirstMoveRandom;
        numOfMoves = 0;

        if(Math.random() < 0.50){
            currentPlayer = 0;
        }
        else{
            currentPlayer = 1;
        }

        firstPlayer = currentPlayer;
    }

    public void play(){
        boolean isGameOver = false;

        while(!isGameOver){
            System.out.println(board);

            if(!board.isRowEmpty(currentPlayer)){
                isGameOver = true;

                board.finishGame(currentPlayer);
            }
            else {
                int currentMove;

                if(isFirstMoveRandom && numOfMoves == 0){
                    currentMove = (new Random()).nextInt(6) + 1;
                    System.out.printf("Player%d starts but made a random move: %d%n", currentPlayer + 1, currentMove);
                }
                else{
                    currentMove = players[currentPlayer].makeMove(currentPlayer, board); //1-6
                }

                int pocketIndex = currentPlayer*board.getNumOfPocketsForPlayer() + currentMove - 1; //players 1 can be ours 6
                boolean canMoveAgain = board.makeMove(currentPlayer, pocketIndex);

                if(!canMoveAgain){
                    currentPlayer = (currentPlayer + 1)%2;
                }

                numOfMoves++;
            }
        }

        System.out.println(board);

        StringBuilder finalMessage = new StringBuilder();

        if(board.getStore(0).getStones() > board.getStore(1).getStones()){
            finalMessage.append("Player1 has won!");
        }
        else if(board.getStore(0).getStones() < board.getStore(1).getStones()){
            finalMessage.append("Player2 has won!");
        }
        else{
            finalMessage.append("It's a draw!");
        }

        finalMessage.append(String.format(" It took %d moves! (Player%d started)\n", numOfMoves, firstPlayer + 1));

        finalMessage.append(String.format("Player1's score: %d | Player2's score: %d",
                board.getStore(0).getStones(), board.getStore(1).getStones()));

        System.out.println(finalMessage);

        for(int i = 0; i < 2; i++){
            if(players[i] instanceof Player){
                ((Player)players[i]).closeReader();
            }
        }
    }
}
