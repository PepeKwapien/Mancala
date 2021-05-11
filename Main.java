import commandLine.MancalaCommandLine;

public class Main {

    public static void main(String[] args) {
        MancalaCommandLine commandLineInterface = new MancalaCommandLine();
        commandLineInterface.generateGameInstanceAndPlay();
        commandLineInterface.close();
    }
}
