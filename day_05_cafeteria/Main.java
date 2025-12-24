package day_5_cafeteria;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    private static final String INPUT_PATH = "src/day_5_cafeteria/input";

    public static void main(String[] args) {
        InventoryManagementSystem inventoryManagementSystem = new InventoryManagementSystem();

        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {
            // Loop to add ingredient id ranges
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.isEmpty()) break;

                inventoryManagementSystem.addRange(IngredientIdRange.fromLine(line));
            }

            // Loop to add available ingredient ids
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                inventoryManagementSystem.addIngredientId(Long.parseLong(line));
            }

            long firstSolution = inventoryManagementSystem.countFreshAvailableIngredients();
            System.out.println(firstSolution);

            long secondSolution = inventoryManagementSystem.countFreshIngredients();
            System.out.println(secondSolution);

        } catch (FileNotFoundException exception) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
