package day_11_reactor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    private static final String INPUT_PATH = "src/day_11_reactor/input";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {
            Reactor reactor = new Reactor();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    reactor.addDevice(line);
                }
            }

            long firstSolution = ReactorSolver.countPaths(reactor, "you", "out");
            System.out.println(firstSolution);

            long secondSolution = ReactorSolver.countPathsWithStops(reactor, "svr", "out", "dac", "fft");
            System.out.println(secondSolution);

        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
