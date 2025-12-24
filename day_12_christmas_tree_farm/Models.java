package day_12_christmas_tree_farm;

import java.util.*;

class Task {
    int w, h;
    Map<Integer, Integer> requirements = new HashMap<>();

    public static Task parse(String line) {
        Task t = new Task();
        String[] parts = line.split(":");
        String[] dims = parts[0].trim().split("x");
        t.w = Integer.parseInt(dims[0]);
        t.h = Integer.parseInt(dims[1]);

        String[] reqs = parts[1].trim().split("\\s+");
        for (int i = 0; i < reqs.length; i++) {
            int count = Integer.parseInt(reqs[i]);
            if (count > 0) t.requirements.put(i, count);
        }
        return t;
    }
}

class Shape {
    int id;
    int area;
    List<Variant> variants = new ArrayList<>();

    public Shape(int id) { this.id = id; }

    public void parseGrid(List<String> rows) {
        int r = rows.size();
        int c = rows.get(0).length();
        boolean[][] raw = new boolean[r][c];
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rows.get(i).charAt(j) == '#') {
                    raw[i][j] = true;
                    count++;
                }
            }
        }
        this.area = count;
        generateVariants(raw);
    }

    private void generateVariants(boolean[][] base) {
        Set<String> seen = new HashSet<>();
        boolean[][] current = base;

        for (int i = 0; i < 4; i++) {
            addVariant(current, seen);
            addVariant(flip(current), seen);
            current = rotate(current);
        }
    }

    private void addVariant(boolean[][] grid, Set<String> seen) {
        boolean[][] normalized = trim(grid);
        String key = Arrays.deepToString(normalized);
        if (!seen.contains(key)) {
            seen.add(key);
            variants.add(new Variant(normalized));
        }
    }

    private boolean[][] rotate(boolean[][] in) {
        int h = in.length;
        int w = in[0].length;
        boolean[][] out = new boolean[w][h];
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                out[c][h - 1 - r] = in[r][c];
            }
        }
        return out;
    }

    private boolean[][] flip(boolean[][] in) {
        int h = in.length;
        int w = in[0].length;
        boolean[][] out = new boolean[h][w];
        for (int r = 0; r < h; r++) {
            out[r] = in[r].clone();
            for(int c=0; c<w/2; c++){
                boolean temp = out[r][c];
                out[r][c] = out[r][w-1-c];
                out[r][w-1-c] = temp;
            }
        }
        return out;
    }

    private boolean[][] trim(boolean[][] in) {
        int minR = in.length, maxR = -1, minC = in[0].length, maxC = -1;
        for (int r = 0; r < in.length; r++) {
            for (int c = 0; c < in[0].length; c++) {
                if (in[r][c]) {
                    if (r < minR) minR = r;
                    if (r > maxR) maxR = r;
                    if (c < minC) minC = c;
                    if (c > maxC) maxC = c;
                }
            }
        }
        int h = maxR - minR + 1;
        int w = maxC - minC + 1;
        boolean[][] out = new boolean[h][w];
        for (int r = 0; r < h; r++) {
            System.arraycopy(in[minR + r], minC, out[r], 0, w);
        }
        return out;
    }
}

class Variant {
    boolean[][] grid;
    int h, w;
    public Variant(boolean[][] grid) {
        this.grid = grid;
        this.h = grid.length;
        this.w = grid[0].length;
    }
}
