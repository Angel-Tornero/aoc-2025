package day_6_trash_compactor;

import java.util.List;

public class Operation {
    private Operator operator;
    private final List<Long> operands;

    public Operation(List<Long> operands, String operator) {
        this.operands = operands;
        if ("+".equals(operator))
            this.operator = Operator.ADD;
        else if ("*".equals(operator))
            this.operator = Operator.MULTIPLY;
    }

    public long solve() {
        if (this.operator == Operator.ADD)
            return operands.stream().mapToLong(Long::valueOf).sum();

        if (this.operator == Operator.MULTIPLY)
            return operands.stream().mapToLong(Long::valueOf).reduce(1, (subtotal, operand) -> subtotal * operand);

        return 0L;
    }
}
