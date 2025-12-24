package day_8_playground;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String INPUT_PATH = "src/day_8_playground/input";
    private static final int EDGE_LIMIT = 1000;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {
            List<Point3D> points = new ArrayList<>();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                long[] numbers = Arrays.stream(line.split(","))
                       .mapToLong(Long::parseLong)
                       .toArray();

                if (numbers.length != 3) {
                    throw new IllegalArgumentException("Each line must have exactly 3 numbers");
                }

                points.add(new Point3D(numbers[0], numbers[1], numbers[2]));
            }

            long firstSolution = CircuitMaker.computeLargestCircuitProduct(points, EDGE_LIMIT);
            System.out.println(firstSolution);

            long secondSolution = CircuitMaker.computeFinalConnectionProduct(points);
            System.out.println(secondSolution);

        } catch (FileNotFoundException exception) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
