package day_6_trash_compactor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CephalopodProblemListParser {
    public static OperationList parse(List<String> fileLines) {
        List<char[]> rows = new ArrayList<>();
        OperationList operationList = new OperationList();

        List<String> operators = NormalProblemListParser.parseOperators(fileLines.getLast());

        for (int lineIndex = 0; lineIndex < fileLines.size() - 1; lineIndex++) {
            rows.add(fileLines.get(lineIndex).toCharArray());
        }

        List<String> columns = new ArrayList<>(Collections.nCopies(rows.getFirst().length, ""));

        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            for (int colIndex = 0; colIndex < rows.get(rowIndex).length; colIndex++) {
                String column = columns.get(colIndex);
                columns.set(colIndex, column + rows.get(rowIndex)[colIndex]);
            }
        }

        List<Long> numbers = new ArrayList<>();
        int operatorIndex = 0;
        for (String column : columns) {
            if (column.isBlank()) {
                operationList.addOperation(new Operation(numbers, operators.get(operatorIndex)));
                numbers = new ArrayList<>();
                operatorIndex++;
                continue;
            }
            numbers.add(Long.valueOf(column.trim()));
        }

        // Add last operation that is not delimited by an blank column
        operationList.addOperation(new Operation(numbers, operators.get(operatorIndex)));

        return operationList;
    }
}
