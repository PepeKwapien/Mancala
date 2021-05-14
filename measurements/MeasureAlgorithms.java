package measurements;

import game.Game;
import players.MinMaxPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MeasureAlgorithms {
    private static int DEFAULT_REPETITIONS = 5;
    private static int DEFAULT_DEPTH = 4;
    private static int DEFAULT_POCKETS = 6;
    private static int DEFAULT_STONES = 4;
    private static String DIRECTORY = "MeasurementsFiles/";

    private static boolean printOutToFile(String path, ArrayList<Number> toWrite){
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path + ".txt", true))) {
            String listString = toWrite.stream().map(Number::toString).collect(Collectors.joining("\t"));
            out.write(listString);
            out.newLine();
        } catch (FileNotFoundException e) {
            File newFile = new File(DIRECTORY);
            if(!newFile.mkdirs()){
                e.printStackTrace();
            }
            else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void measureMinMaxNTimes(int repetitions, boolean alphaBetaPruning, int maxDepth,
                                           int pockets, int stones){
        repetitions = repetitions > 0 ? repetitions : DEFAULT_REPETITIONS;
        maxDepth = maxDepth > 1 ? maxDepth : DEFAULT_DEPTH;
        pockets = pockets > 0 ? pockets : DEFAULT_POCKETS;
        stones = stones > 0 ? stones : DEFAULT_STONES;

        for(int i = 0; i < repetitions; i++){
            Game iterationGame = new Game(new MinMaxPlayer(maxDepth, alphaBetaPruning, false),
                    new MinMaxPlayer(maxDepth, alphaBetaPruning, false), pockets, stones, false);
            iterationGame.play(false);
            String fileName = String.format("%s-%d-%d-%d",alphaBetaPruning ? "ABvsAB" : "MMvsMM",
                    maxDepth, pockets, stones);

            if(!printOutToFile(DIRECTORY + fileName, iterationGame.getMeasurements())){
                i--;
            }
        }
    }
}
