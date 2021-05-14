import commandLine.MancalaCommandLine;
import measurements.MeasureAlgorithms;

public class Main {

    public static void main(String[] args) {
        /*MancalaCommandLine commandLineInterface = new MancalaCommandLine();
        commandLineInterface.generateGameInstanceAndPlay();
        commandLineInterface.close();*/

        MeasureAlgorithms.measureMinMaxNTimes(10, false, 4, 6, 4);
        MeasureAlgorithms.measureMinMaxNTimes(10, true, 4, 6, 4);
    }
}
