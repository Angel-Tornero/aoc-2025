package day_11_reactor;

import java.util.*;

public class ReactorSolver {

    public static long countPaths(Reactor reactor, String start, String end) {
        return dfs(start, end, reactor.adj, new HashMap<>());
    }

    public static long countPathsWithStops(Reactor reactor, String start, String end, String stop1, String stop2) {
        long route1 = countPaths(reactor, start, stop1) * countPaths(reactor, stop1, stop2) * countPaths(reactor, stop2, end);

        long route2 = countPaths(reactor, start, stop2) * countPaths(reactor, stop2, stop1) * countPaths(reactor, stop1, end);

        return route1 + route2;
    }

    private static long dfs(String current, String target, Map<String, List<String>> graph, Map<String, Long> memo) {
        if (current.equals(target)) return 1;
        if (memo.containsKey(current)) return memo.get(current);

        long count = 0;
        List<String> neighbors = graph.get(current);

        if (neighbors != null) {
            for (String neighbor : neighbors) {
                count += dfs(neighbor, target, graph, memo);
            }
        }

        memo.put(current, count);
        return count;
    }
}
