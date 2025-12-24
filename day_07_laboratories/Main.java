package day_7_laboratories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String INPUT_PATH = "src/day_7_laboratories/input";

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {
            List<String> fileLines = new ArrayList<>();

            while (scanner.hasNext()) {
                fileLines.add(scanner.nextLine());
            }

            TachyonManifold tachyonManifold = new TachyonManifold(fileLines);

            long firstSolution = tachyonManifold.countBeamSplits();
            System.out.println(firstSolution);

            long secondSolution = tachyonManifold.countBeamPaths();
            System.out.println(secondSolution);

        } catch (FileNotFoundException exception) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
