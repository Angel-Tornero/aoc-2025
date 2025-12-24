package day_10_factory;

import java.util.ArrayList;
import java.util.List;

public class FactorySolver {

    public static int solveLights(Machine machine) {
        int m = machine.buttons.size();
        int best = Integer.MAX_VALUE;

        for (int subset = 0; subset < (1 << m); subset++) {
            int state = 0;
            int presses = 0;

            for (int i = 0; i < m; i++) {
                if ((subset & (1 << i)) != 0) {
                    state ^= machine.buttons.get(i).bitMask;
                    presses++;
                }
            }

            if (state == machine.lightTarget) {
                best = Math.min(best, presses);
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
    }

    public static long solveJoltage(Machine machine) {
        int rows = machine.joltageTarget.length;
        int cols = machine.buttons.size();

        double[][] mat = new double[rows][cols + 1];

        for (int r = 0; r < rows; r++) {
            mat[r][cols] = machine.joltageTarget[r];
            for (int c = 0; c < cols; c++) {
                mat[r][c] = machine.buttons.get(c).counters[r];
            }
        }

        int pivotRow = 0;
        List<Integer> pivotCols = new ArrayList<>();
        List<Integer> freeCols = new ArrayList<>();

        for (int c = 0; c < cols && pivotRow < rows; c++) {
            int sel = -1;
            for (int r = pivotRow; r < rows; r++) {
                if (Math.abs(mat[r][c]) > 1e-9) {
                    sel = r;
                    break;
                }
            }

            if (sel == -1) {
                freeCols.add(c);
                continue;
            }

            double[] temp = mat[pivotRow];
            mat[pivotRow] = mat[sel];
            mat[sel] = temp;

            double div = mat[pivotRow][c];
            for (int j = c; j <= cols; j++) {
                mat[pivotRow][j] /= div;
            }

            for (int r = 0; r < rows; r++) {
                if (r != pivotRow && Math.abs(mat[r][c]) > 1e-9) {
                    double factor = mat[r][c];
                    for (int j = c; j <= cols; j++) {
                        mat[r][j] -= factor * mat[pivotRow][j];
                    }
                }
            }

            pivotCols.add(c);
            pivotRow++;
        }

        for (int c = 0; c < cols; c++) {
            if (!pivotCols.contains(c) && !freeCols.contains(c)) {
                freeCols.add(c);
            }
        }

        for (int r = pivotRow; r < rows; r++) {
            if (Math.abs(mat[r][cols]) > 1e-5) return 0;
        }

        long result = searchFreeVars(mat, pivotCols, freeCols, new long[cols], 0);
        return result == Long.MAX_VALUE ? 0 : result;
    }

    private static long searchFreeVars(double[][] mat, List<Integer> pivotCols, List<Integer> freeCols,
                                       long[] solution, int freeIdx) {
        if (freeIdx == freeCols.size()) {
            long currentPresses = 0;
            int cols = solution.length;

            for (int i = pivotCols.size() - 1; i >= 0; i--) {
                int pCol = pivotCols.get(i);
                int pRow = i;

                double val = mat[pRow][cols];
                for (int fCol : freeCols) {
                    val -= mat[pRow][fCol] * solution[fCol];
                }

                long rounded = Math.round(val);
                if (Math.abs(val - rounded) > 1e-4 || rounded < 0) {
                    return Long.MAX_VALUE;
                }
                solution[pCol] = rounded;
            }

            for (long x : solution) currentPresses += x;
            return currentPresses;
        }

        long best = Long.MAX_VALUE;
        int fCol = freeCols.get(freeIdx);

        for (long val = 0; val < 200; val++) {
            solution[fCol] = val;
            long res = searchFreeVars(mat, pivotCols, freeCols, solution, freeIdx + 1);
            best = Math.min(best, res);
        }

        return best;
    }
}
