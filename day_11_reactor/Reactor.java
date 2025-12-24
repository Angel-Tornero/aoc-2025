package day_11_reactor;

import java.util.*;

public class Reactor {
    public final Map<String, List<String>> adj = new HashMap<>();

    public void addDevice(String line) {
        String[] parts = line.split(": ");
        String source = parts[0];

        if (parts.length > 1) {
            String[] targets = parts[1].split(" ");
            adj.put(source, Arrays.asList(targets));
        } else {
            adj.put(source, Collections.emptyList());
        }
    }
}
