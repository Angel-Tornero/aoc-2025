package day_3_lobby;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String INPUT_PATH = "src/day_3_lobby/input";
    private static final int BATTERIES_FOR_PART_ONE = 2;
    private static final int BATTERIES_FOR_PART_TWO = 12;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {

            List<BatteryBank> allBanks = new ArrayList<>();

            while (scanner.hasNext()) {
                allBanks.add(BatteryBank.fromLine(scanner.next()));
            }

            BatterySystem batterySystem = new BatterySystem(allBanks);

            long firstSolution = batterySystem.getLargestPossibleJoltage(BATTERIES_FOR_PART_ONE);
            System.out.println(firstSolution);

            long secondSolution = batterySystem.getLargestPossibleJoltage(BATTERIES_FOR_PART_TWO);
            System.out.println(secondSolution);

        } catch (FileNotFoundException exception) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
