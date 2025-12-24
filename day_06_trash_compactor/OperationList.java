package day_6_trash_compactor;

import java.util.ArrayList;
import java.util.List;

public class OperationList {
    private final List<Operation> operations;

    public OperationList() {
        this.operations = new ArrayList<>();
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    public long solve() {
        return this.operations
                .stream()
                .mapToLong(Operation::solve)
                .sum();
    }
}
