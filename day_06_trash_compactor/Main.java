package day_6_trash_compactor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String INPUT_PATH = "src/day_6_trash_compactor/input";

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {
            List<String> fileLines = new ArrayList<>();

            while (scanner.hasNext()) {
                fileLines.add(scanner.nextLine());
            }

            OperationList normallyParsedProblemList = NormalProblemListParser.parse(fileLines);

            long firstSolution = normallyParsedProblemList.solve();
            System.out.println(firstSolution);

            OperationList cephalopodParsedProblemList = CephalopodProblemListParser.parse(fileLines);
            long secondSolution = cephalopodParsedProblemList.solve();
            System.out.println(secondSolution);

        } catch (FileNotFoundException exception) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
