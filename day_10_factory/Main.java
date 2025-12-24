package day_10_factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    private static final String INPUT_PATH = "src/day_10_factory/input";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {

            long firstSolution = 0;
            long secondSolution = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                Machine machine = Machine.parse(line);

                firstSolution += FactorySolver.solveLights(machine);
                secondSolution += FactorySolver.solveJoltage(machine);
            }

            System.out.println(firstSolution);
            System.out.println(secondSolution);

        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
