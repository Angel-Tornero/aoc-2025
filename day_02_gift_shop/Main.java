package day_2_gift_shop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final String INPUT_PATH = "src/day_2_gift_shop/input";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(INPUT_PATH))) {

            List<ProductIdRange> allProductIdRanges = new ArrayList<>();

            String[] rangeStrings = scanner.next().split(",");

            for (String rangeStr : rangeStrings) {
                allProductIdRanges.add(ProductIdRange.fromLine(rangeStr));
            }

            long firstSolution = InvalidProductIdProcesser.sumAllTwiceRepeatedSequenceIds(allProductIdRanges);
            System.out.println(firstSolution);

            long secondSolution = InvalidProductIdProcesser.sumAllMultiRepeatedSequenceIds(allProductIdRanges);
            System.out.println(secondSolution);

        } catch (FileNotFoundException exception) {
            System.err.println("Input file not found: " + INPUT_PATH);
        }
    }
}
