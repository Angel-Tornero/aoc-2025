package day_6_trash_compactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalProblemListParser {

    public static List<Long> parseNumbers(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();
    }

    public static List<String> parseOperators(String line) {
        return Arrays
                .stream(line.trim().split("\\s+"))
                .toList();
    }

    public static OperationList parse(List<String> fileLines) {
        OperationList operationList = new OperationList();

        List<String> operators = parseOperators(fileLines.getLast());
        List<List<Long>> numbers = new ArrayList<>();

        for (int lineIndex = 0; lineIndex < fileLines.size() - 1; lineIndex++) {
            numbers.add(parseNumbers(fileLines.get(lineIndex)));
        }

        for (int colIndex = 0; colIndex < operators.size(); colIndex++) {
            List<Long> operands = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < numbers.size(); rowIndex++) {
                operands.add(numbers.get(rowIndex).get(colIndex));
            }
            operationList.addOperation(new Operation(operands, operators.get(colIndex)));
        }

        return operationList;
    }
}
