package day_9_movie_theater;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final String INPUT_PATH = "src/day_9_movie_theater/input";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {

            List<Point> points = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                points.add(new Point(x, y));
            }

            RectangleSolver solver = new RectangleSolver(points);

            long firstSolution = solver.findLargestRectangle();
            System.out.println(firstSolution);

            long secondSolution = solver.findLargestRectangleWithGreen();
            System.out.println(secondSolution);

        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
