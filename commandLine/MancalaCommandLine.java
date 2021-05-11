package commandLine;

import game.Game;
import players.IPlayer;
import players.MinMaxPlayer;
import players.Player;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MancalaCommandLine {

    private final int DEFAULT_TREE_DEPTH = 4;
    private final int DEFAULT_NUM_POCKETS = 6;
    private final int DEFAULT_NUM_STONES = 4;
    private final boolean DEFAULT_RANDOM_FST_MOVE = true;
    private final Scanner scanner;

    public MancalaCommandLine(){
        scanner = new Scanner(System.in);
    }

    private String askToPlay(){
        System.out.println("Do you want to play a new Mancala Game? (Answer: y/n Default: y)");
        return scanner.nextLine();
    }

    private IPlayer askWhichPlayer(int playersNumber){
        System.out.printf("Player%d should be (default: Human):\n" +
                        "1 - Human\n" +
                        "2 - MinMaxBot%n",
                playersNumber);

        int answer = askForPositiveNumberWithDefaultValue(1);

        if(answer == 1){
            return new Player();
        }
        else if(answer == 2){
            return askAboutMinMax();
        }
        else{
            System.out.println("There is no such mode. Try again!");
            return askWhichPlayer(playersNumber);
        }
    }

    private MinMaxPlayer askAboutMinMax(){
        System.out.printf("How deep do you want your MinMaxTree to be? (type small positive number - default: %d)%n",
                DEFAULT_TREE_DEPTH);
        String answer = scanner.nextLine();
        if(answer.isEmpty()){
            return new MinMaxPlayer(DEFAULT_TREE_DEPTH);
        }
        else{
            int numAnswer;
            try{
                numAnswer = Integer.parseInt(answer);
            } catch(NumberFormatException nfe){
                System.out.println("This is not a number! Try again!");
                return askAboutMinMax();
            }

            if(numAnswer < 0){
                System.out.println("Provide positive number (greater than 0)! Try again!");
                return askAboutMinMax();
            }
            else{
                return new MinMaxPlayer(numAnswer);
            }
        }
    }

    private int askForPositiveNumberWithDefaultValue(int defaultValue){
        String answer = scanner.nextLine();
        if(answer.isEmpty()){
            return defaultValue;
        }
        else{
            int numAnswer;
            try{
                numAnswer = Integer.parseInt(answer);
            } catch(NumberFormatException nfe){
                System.out.println("This is not a number! Try again!");
                return askForPositiveNumberWithDefaultValue(defaultValue);
            }

            if(numAnswer < 0){
                System.out.println("Provide positive number (greater than 0)! Try again!");
                return askForPositiveNumberWithDefaultValue(defaultValue);
            }
            else{
                return numAnswer;
            }
        }
    }

    private int askForPockets(){
        System.out.printf("How many pockets do you want each player to have?" +
                "(type small positive number - default: %d)%n", DEFAULT_NUM_POCKETS);
        return askForPositiveNumberWithDefaultValue(DEFAULT_NUM_POCKETS);
    }

    private int askForStones(){
        System.out.printf("How many stones do you want each pocket to contain in the beginning?" +
                        "(type small positive number - default: %d)%n", DEFAULT_NUM_STONES);
        return askForPositiveNumberWithDefaultValue(DEFAULT_NUM_STONES);
    }

    private boolean askForRandomFirstMove(){
        System.out.printf("Being able to move first is often crucial in Mancala." +
                "Do you want first player to make a random move? (Answer: y/n Default: %s)%n",
                DEFAULT_RANDOM_FST_MOVE ? "y" : "n");
        String answer = scanner.nextLine();

        if(answer.isEmpty()){
            return DEFAULT_RANDOM_FST_MOVE;
        }
        if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
            return false;
        }
        else if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("ye")
                || answer.equalsIgnoreCase("yes")) {
            return true;
        }
        else{
            System.out.println("Looks like I didn't understand you. Type 'y'/'yes' or 'n'/'no'");
            return askForRandomFirstMove();
        }
    }

    public void generateGameInstanceAndPlay(){
        System.out.println("##### LET'S PLAY MANCALA! #####\n");

        boolean wantToPlayAgain = true;

        while(wantToPlayAgain){
            try {
                while (wantToPlayAgain) {
                    String answer = askToPlay();

                    if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                        wantToPlayAgain = false;
                        System.out.println("See you next time!\nThanks for playing!");
                    } else if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("ye")
                            || answer.equalsIgnoreCase("yes") || answer.isEmpty()) {
                        IPlayer player1 = askWhichPlayer(1);
                        IPlayer player2 = askWhichPlayer(2);

                        int numPockets = askForPockets();
                        int numStones = askForStones();

                        boolean firstRandomMove = askForRandomFirstMove();
                        System.out.println("\n##### LET US BEGIN! #####\n");
                        (new Game(player1, player2, numPockets, numStones, firstRandomMove)).play();
                    }
                    else{
                        System.out.println("Looks like I didn't understand you. Type 'y'/'yes' or 'n'/'no'");
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("\nFollow instructions!\n");
            }

            scanner.close();
        }
    }

    public void close(){
        scanner.close();
    }
}
