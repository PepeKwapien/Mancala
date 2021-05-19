package measurements;

import assessBoard.IAssessBoard;
import assessBoard.MyEmptyPockets;
import assessBoard.PointDifference;
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

    public static void measureMinMaxNTimes(int repetitions, boolean alphaBetaPruning1, boolean alphaBetaPruning2,
                                           int maxDepth1, int maxDepth2, int pockets, int stones,
                                           IAssessBoard iAssessBoard1, IAssessBoard iAssessBoard2){
        repetitions = repetitions > 0 ? repetitions : DEFAULT_REPETITIONS;
        maxDepth1 = maxDepth1 > 1 ? maxDepth1 : DEFAULT_DEPTH;
        maxDepth2 = maxDepth2 > 1 ? maxDepth2 : DEFAULT_DEPTH;
        pockets = pockets > 0 ? pockets : DEFAULT_POCKETS;
        stones = stones > 0 ? stones : DEFAULT_STONES;

        for(int i = 0; i < repetitions; i++){
            Game iterationGame = new Game(new MinMaxPlayer(maxDepth1, alphaBetaPruning1, false,
                    iAssessBoard1), new MinMaxPlayer(maxDepth2, alphaBetaPruning2,
                    false, iAssessBoard2), pockets, stones, false);
            iterationGame.play(false);

            String fileName = String.format("%s-%d-%s_vs_%s-%d-%s_%d_%d",alphaBetaPruning1 ? "AB" : "MM", maxDepth1,
                    getABName(iAssessBoard1), alphaBetaPruning2 ? "AB" : "MM", maxDepth2, getABName(iAssessBoard2),
                    pockets, stones);

            if(!printOutToFile(DIRECTORY + fileName, iterationGame.getMeasurements())){
                i--;
            }
        }
    }

    private static String getABName(IAssessBoard iAssessBoard){
        String name = "MTEP";
        if(iAssessBoard instanceof PointDifference){
            name = "PD";
        }
        else if (iAssessBoard instanceof MyEmptyPockets){
            name = "MEP";
        }

        return name;
    }
}
