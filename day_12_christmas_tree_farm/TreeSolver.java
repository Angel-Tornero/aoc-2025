package day_12_christmas_tree_farm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeSolver {

    public static boolean canFit(Task task, List<Shape> allShapes) {
        List<Shape> toPlace = new ArrayList<>();
        int totalArea = 0;

        for (Map.Entry<Integer, Integer> entry : task.requirements.entrySet()) {
            Shape s = allShapes.get(entry.getKey());
            for (int i = 0; i < entry.getValue(); i++) {
                toPlace.add(s);
                totalArea += s.area;
            }
        }

        if (totalArea > task.w * task.h) return false;

        // Optimization: Sort largest shapes first
        toPlace.sort((a, b) -> b.area - a.area);

        boolean[][] board = new boolean[task.h][task.w];
        return backtrack(board, toPlace, 0, 0);
    }

    private static boolean backtrack(boolean[][] board, List<Shape> list, int idx, int lastPos) {
        if (idx == list.size()) return true;

        Shape p = list.get(idx);
        int H = board.length;
        int W = board[0].length;

        // Enforce ordering for identical shapes to reduce permutations
        boolean sameAsPrev = (idx > 0 && list.get(idx - 1).id == p.id);
        int start = sameAsPrev ? lastPos : 0;

        for (int pos = start; pos < H * W; pos++) {
            int r = pos / W;
            int c = pos % W;

            for (Variant v : p.variants) {
                if (r + v.h > H || c + v.w > W) continue;

                if (fits(board, r, c, v)) {
                    place(board, r, c, v, true);

                    if (backtrack(board, list, idx + 1, pos)) return true;

                    place(board, r, c, v, false);
                }
            }
        }

        return false;
    }

    private static boolean fits(boolean[][] board, int r, int c, Variant v) {
        for (int i = 0; i < v.h; i++) {
            for (int j = 0; j < v.w; j++) {
                if (v.grid[i][j] && board[r + i][c + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void place(boolean[][] board, int r, int c, Variant v, boolean val) {
        for (int i = 0; i < v.h; i++) {
            for (int j = 0; j < v.w; j++) {
                if (v.grid[i][j]) {
                    board[r + i][c + j] = val;
                }
            }
        }
    }
}
