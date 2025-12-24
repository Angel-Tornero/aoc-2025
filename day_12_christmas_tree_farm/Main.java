package day_12_christmas_tree_farm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String INPUT_PATH = "src/day_12_christmas_tree_farm/input";

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_PATH))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) lines.add(line);
            }

            List<Shape> shapes = new ArrayList<>();
            List<Task> tasks = new ArrayList<>();
            parseInput(lines, shapes, tasks);

            long count = 0;
            for (Task task : tasks) {
                if (TreeSolver.canFit(task, shapes)) {
                    count++;
                }
            }

            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseInput(List<String> lines, List<Shape> shapes, List<Task> tasks) {
        Shape currentShape = null;
        List<String> shapeBuffer = new ArrayList<>();

        for (String s : lines) {
            s = s.trim();

            if (s.contains("x") && s.contains(":")) {
                if (currentShape != null) {
                    currentShape.parseGrid(shapeBuffer);
                    shapes.add(currentShape);
                    currentShape = null;
                    shapeBuffer.clear();
                }
                tasks.add(Task.parse(s));
                continue;
            }

            if (s.matches("\\d+:")) {
                if (currentShape != null) {
                    currentShape.parseGrid(shapeBuffer);
                    shapes.add(currentShape);
                }
                int id = Integer.parseInt(s.substring(0, s.length() - 1));
                currentShape = new Shape(id);
                shapeBuffer.clear();
                continue;
            }

            if (currentShape != null) {
                shapeBuffer.add(s);
            }
        }

        if (currentShape != null) {
            currentShape.parseGrid(shapeBuffer);
            shapes.add(currentShape);
        }
    }
}