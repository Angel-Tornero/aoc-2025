package day_9_movie_theater;

import java.util.List;

class RectangleSolver {
    private final List<Point> points;

    RectangleSolver(List<Point> points) {
        this.points = points;
    }

    long findLargestRectangle() {
        long maxArea = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point a = points.get(i);
                Point b = points.get(j);
                long width = Math.abs((long) a.x - b.x) + 1;
                long height = Math.abs((long) a.y - b.y) + 1;
                long area = width * height;
                if (area > maxArea) maxArea = area;
            }
        }
        return maxArea;
    }

    long findLargestRectangleWithGreen() {
        long maxArea = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < n; j++) {
                Point p2 = points.get(j);

                long width = Math.abs((long) p1.x - p2.x) + 1;
                long height = Math.abs((long) p1.y - p2.y) + 1;
                long area = width * height;

                if (area <= maxArea) continue;

                if (isValidRectangle(p1, p2)) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    private boolean isValidRectangle(Point p1, Point p2) {
        int minX = Math.min(p1.x, p2.x);
        int maxX = Math.max(p1.x, p2.x);
        int minY = Math.min(p1.y, p2.y);
        int maxY = Math.max(p1.y, p2.y);

        Point c1 = new Point(minX, maxY);
        Point c2 = new Point(maxX, minY);

        if (!isPointInsideOrOnBoundary(c1)) return false;
        if (!isPointInsideOrOnBoundary(c2)) return false;

        if (polygonEdgesIntersectRect(minX, maxX, minY, maxY)) return false;

        return true;
    }

    private boolean isPointInsideOrOnBoundary(Point p) {
        int n = points.size();
        for (int i = 0; i < n; i++) {
            Point a = points.get(i);
            Point b = points.get((i + 1) % n);
            if (isPointOnSegment(p, a, b)) return true;
        }

        int intersections = 0;
        for (int i = 0; i < n; i++) {
            Point a = points.get(i);
            Point b = points.get((i + 1) % n);

            if (a.x == b.x) {
                if (a.x > p.x) {
                    int yMin = Math.min(a.y, b.y);
                    int yMax = Math.max(a.y, b.y);
                    if (p.y >= yMin && p.y < yMax) {
                        intersections++;
                    }
                }
            }
        }
        return (intersections % 2) == 1;
    }

    private boolean isPointOnSegment(Point p, Point a, Point b) {
        if (a.x == b.x) {
            return p.x == a.x && p.y >= Math.min(a.y, b.y) && p.y <= Math.max(a.y, b.y);
        } else {
            return p.y == a.y && p.x >= Math.min(a.x, b.x) && p.x <= Math.max(a.x, b.x);
        }
    }

    private boolean polygonEdgesIntersectRect(int minX, int maxX, int minY, int maxY) {
        if (minX + 1 >= maxX || minY + 1 >= maxY) return false;

        int n = points.size();
        for (int i = 0; i < n; i++) {
            Point a = points.get(i);
            Point b = points.get((i + 1) % n);

            if (a.x == b.x) {
                if (a.x > minX && a.x < maxX) {
                    int edgeMinY = Math.min(a.y, b.y);
                    int edgeMaxY = Math.max(a.y, b.y);
                    if (Math.max(edgeMinY, minY) < Math.min(edgeMaxY, maxY)) {
                        return true;
                    }
                }
            } else {
                if (a.y > minY && a.y < maxY) {
                    int edgeMinX = Math.min(a.x, b.x);
                    int edgeMaxX = Math.max(a.x, b.x);
                    if (Math.max(edgeMinX, minX) < Math.min(edgeMaxX, maxX)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
