package day_7_laboratories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TachyonManifold {
    private final List<String> rows;

    public TachyonManifold(List<String> rows) {
        this.rows = rows;
    }

    public int countBeamSplits() {
        int count = 0;
        List<Boolean> currentBeams = this.rows.getFirst()
                .chars()
                .mapToObj(c -> (char) c == 'S')
                .toList();

        for (String row : this.rows.subList(1, rows.size())) {
            BeamResult result = computeBeamStep(row, currentBeams);
            currentBeams = result.beams();
            count += result.count();
        }

        return count;
    }

    public static BeamResult computeBeamStep(String nextRow, List<Boolean> currentBeams) {
        List<Boolean> newBeams = new ArrayList<>(Collections.nCopies(currentBeams.size(), false));
        int splits = 0;

        for (int column = 0; column < currentBeams.size(); column++) {
            // Case there is no beam
            if (!currentBeams.get(column))
                continue;

            // Case there is beam and empty space
            if (nextRow.charAt(column) == '.') {
                newBeams.set(column, true);
                continue;
            }

            // Case there is beam and splitter
            if (column > 0)
                newBeams.set(column - 1, true);
            if (column < currentBeams.size() - 1)
                newBeams.set(column + 1, true);

            splits++;
        }

        return new BeamResult(newBeams, splits);
    }

    public long countBeamPaths() {
        List<Long> currentPaths = this.rows.getFirst()
                .chars()
                .mapToObj(c -> (char) c == 'S' ? 1L : 0L)
                .toList();

        for (String row : this.rows.subList(1, rows.size())) {
            currentPaths = computePathStep(row, currentPaths);
        }

        return currentPaths.stream().mapToLong(Long::valueOf).sum();
    }

    public static List<Long> computePathStep(String nextRow, List<Long> currentPaths) {
        List<Long> newPaths = new ArrayList<>(currentPaths);

        for (int column = 0; column < currentPaths.size(); column++) {
            // Case there is no beam
            if (currentPaths.get(column) == 0)
                continue;

            // Case there is beam and empty space
            if (nextRow.charAt(column) == '.') {
                continue;
            }

            // Case there is beam and splitter
            newPaths.set(column, 0L);
            if (column > 0) {
                newPaths.set(column - 1, newPaths.get(column - 1) + currentPaths.get(column));
            }
                
            if (column < currentPaths.size() - 1) {
                newPaths.set(column + 1, newPaths.get(column + 1) + currentPaths.get(column));
            }
        }

        return newPaths;
    }
}
