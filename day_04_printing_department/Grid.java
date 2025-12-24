package day_4_printing_department;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {
    private List<char[]> cells;

    public Grid() {
        this.cells = new ArrayList<>();
    }

    private static List<char[]> copyCells(List<char[]> cells) {
        return cells.stream()
                .map(char[]::clone)
                .toList();
    }

    public void addRowFromLine(String line) throws IllegalArgumentException {
        if (!line.matches("[@.]+")) {
            throw new IllegalArgumentException("Row can only contain '@' or '.'");
        }
        cells.add(line.toCharArray());
    }

    public int countAccessible(int limitAllowedAdjacentRolls) {
        return IntStream.range(0, cells.size())
                .map(row ->
                        (int) IntStream.range(0, cells.get(row).length)
                                .filter(col -> cells.get(row)[col] == '@' &&
                                        isAccessible(cells, row, col, limitAllowedAdjacentRolls))
                                .count()
                )
                .sum();
    }

    private static boolean isAccessible(List<char[]> cells, int rowIndex, int columnIndex, int limitAllowedAdjacentRolls) {
        int adjacentRollsCount = 0;
        for (int row = rowIndex - 1; row < rowIndex + 2; row++) {
            if (row < 0 || row >= cells.size()) continue;
            for (int column = columnIndex - 1; column < columnIndex + 2; column++) {
                if (column < 0 || column >= cells.get(row).length) continue;
                if (row == rowIndex && column == columnIndex) continue;
                if (cells.get(row)[column] == '@') adjacentRollsCount++;
            }
        }
        return adjacentRollsCount < limitAllowedAdjacentRolls;
    }

    public int countAccessibleWithRemovals(int limitAllowedAdjacentRolls) {
        int globalCount = 0;
        int count;
        List<char[]> current = copyCells(this.cells);
        List<char[]> updated = copyCells(this.cells);
        do {
            count = 0;
            for (int i = 0; i < current.size(); i++)
                for (int j = 0; j < current.get(i).length; j++) {
                    if (current.get(i)[j] != '@') continue;
                    if (isAccessible(current, i, j, limitAllowedAdjacentRolls)) {
                        count++;
                        updated.get(i)[j] = '.';
                    }
                }
            globalCount += count;
            current = copyCells(updated);
        } while (count > 0);
        return globalCount;
    }
}
