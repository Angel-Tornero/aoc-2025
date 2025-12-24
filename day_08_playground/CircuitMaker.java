package day_8_playground;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircuitMaker {

    public static long computeLargestCircuitProduct(List<Point3D> points, int edgeLimit) {

        int n = points.size();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point3D p1 = points.get(i);
                Point3D p2 = points.get(j);
                long dist = squaredDistance(p1, p2);
                edges.add(new Edge(i, j, dist));
            }
        }

        edges.sort(Comparator.comparingLong(edge -> edge.distance));
        List<Edge> selectedEdges = edges.subList(0, Math.min(edgeLimit, edges.size()));

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (Edge edge : selectedEdges) {
            dsu.union(edge.index1, edge.index2);
        }

        Map<Integer, Integer> componentSizes = new HashMap<>();
        for (int index = 0; index < n; index++) {
            int root = dsu.find(index);
            componentSizes.merge(root, 1, Integer::sum);
        }

        return componentSizes.values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Integer::longValue)
                .reduce(1L, (x, y) -> x * y);
    }

    public static long computeFinalConnectionProduct(List<Point3D> points) {

        int n = points.size();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point3D p1 = points.get(i);
                Point3D p2 = points.get(j);
                long dist = squaredDistance(p1, p2);
                edges.add(new Edge(i, j, dist));
            }
        }

        edges.sort(Comparator.comparingLong(edge -> edge.distance));

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int components = n;

        for (Edge edge : edges) {
            if (dsu.find(edge.index1) != dsu.find(edge.index2)) {
                dsu.union(edge.index1, edge.index2);
                components--;

                if (components == 1) {
                    Point3D a = points.get(edge.index1);
                    Point3D b = points.get(edge.index2);
                    return a.x() * b.x();
                }
            }
        }

        throw new IllegalStateException("Points never became fully connected");
    }

    private static long squaredDistance(Point3D p1, Point3D p2) {
        long dx = p1.x() - p2.x();
        long dy = p1.y() - p2.y();
        long dz = p1.z() - p2.z();
        return dx * dx + dy * dy + dz * dz;
    }

    private static record Edge(int index1, int index2, long distance) {
    }

    private static class DisjointSetUnion {
        int[] parent;
        int[] size;

        DisjointSetUnion(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int index) {
            if (parent[index] != index)
                parent[index] = find(parent[index]);
            return parent[index];
        }

        void union(int index1, int index2) {
            int root1 = find(index1);
            int root2 = find(index2);
            if (root1 == root2)
                return;

            if (size[root1] < size[root2]) {
                int tmp = root1;
                root1 = root2;
                root2 = tmp;
            }

            parent[root2] = root1;
            size[root1] += size[root2];
        }
    }
}
