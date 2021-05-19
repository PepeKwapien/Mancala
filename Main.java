import assessBoard.MyTheirEmptyPockets;
import commandLine.MancalaCommandLine;
import measurements.MeasureAlgorithms;

public class Main {

    public static void main(String[] args) {
        /*MancalaCommandLine commandLineInterface = new MancalaCommandLine();
        commandLineInterface.generateGameInstanceAndPlay();
        commandLineInterface.close();*/
        MeasureAlgorithms.measureMinMaxNTimes(10, true, false, 6, 6,
                6, 4, new MyTheirEmptyPockets(), new MyTheirEmptyPockets());
    }
}
