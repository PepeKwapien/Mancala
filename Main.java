import assessBoard.MyEmptyPockets;
import assessBoard.MyTheirEmptyPockets;
import assessBoard.PointsDifference;
import commandLine.MancalaCommandLine;
import measurements.MeasureAlgorithms;

public class Main {

    public static void main(String[] args) {
        MancalaCommandLine commandLineInterface = new MancalaCommandLine();
        commandLineInterface.generateGameInstanceAndPlay();
        commandLineInterface.close();

        // 1

        /*MeasureAlgorithms.measureMinMaxNTimes(5, false, false, 6,
                6, 6, 4, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 6,
                6, 6, 4, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, false, 6,
                6, 6, 4, new PointsDifference(), new PointsDifference());


        MeasureAlgorithms.measureMinMaxNTimes(5, false, false, 6,
                6, 8, 5, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 6,
                6, 8, 5, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, false, 6,
                6, 8, 5, new PointsDifference(), new PointsDifference());


        MeasureAlgorithms.measureMinMaxNTimes(5, false, false, 6,
                6, 10, 6, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 6,
                6, 10, 6, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, false, 6,
                6, 10, 6, new PointsDifference(), new PointsDifference());*/


        // 2

        /*MeasureAlgorithms.measureMinMaxNTimes(5, true, false, 6,
                10, 6, 4, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, false, true, 6,
                10, 6, 4, new PointsDifference(), new PointsDifference());

        MeasureAlgorithms.measureMinMaxNTimes(5, true, false, 8,
                12, 6, 4, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, false, true, 8,
                12, 6, 4, new PointsDifference(), new PointsDifference());*/

        // 3

        /*MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 6, 4, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 6, 4, new MyEmptyPockets(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 6, 4, new MyTheirEmptyPockets(), new PointsDifference());

        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 9, 6, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 9, 6, new MyEmptyPockets(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 9, 6, new MyTheirEmptyPockets(), new PointsDifference());

        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 12, 8, new PointsDifference(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 12, 8, new MyEmptyPockets(), new PointsDifference());
        MeasureAlgorithms.measureMinMaxNTimes(5, true, true, 10,
                10, 12, 8, new MyTheirEmptyPockets(), new PointsDifference());*/
    }
}
