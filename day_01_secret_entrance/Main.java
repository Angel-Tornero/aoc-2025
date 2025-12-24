package day_1_secret_entrance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String INPUT_PATH = "src/day_1_secret_entrance/input";
    private static final int DIAL_SIZE = 100;
    private static final int DIAL_INITIAL_POSITION = 50;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {

            List<DialRotation> allRotations = new ArrayList<>();

            while (scanner.hasNext()) {
                allRotations.add(DialRotation.fromLine(scanner.next()));
            }

            Dial dial = new Dial(DIAL_SIZE, DIAL_INITIAL_POSITION);

            System.out.println(dial.getEndingZeroCounter(allRotations));
            System.out.println(dial.getAnytimeZeroCounter(allRotations));

        } catch (FileNotFoundException exception) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
