package day_2_gift_shop;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class InvalidProductIdProcesser {
    /**
     * Calculate the sum of all product ids consisting in two equal subsequences
     * within the given ranges (e.g. 12341234)
     */
    public static long sumAllTwiceRepeatedSequenceIds(List<ProductIdRange> productIdRanges) {
        return productIdRanges.stream()
                .flatMapToLong(range -> LongStream.rangeClosed(range.getInitialId(), range.getFinalId()))
                .filter(InvalidProductIdProcesser::isTwiceRepeated)
                .sum();
    }

    /**
     * Helper: Checks if a number is composed of two identical halves
     */
    private static boolean isTwiceRepeated(long number) {
        String s = String.valueOf(number);
        int len = s.length();

        if (len % 2 != 0)
            return false;

        return s.substring(0, len / 2).equals(s.substring(len / 2));
    }

    /**
     * Calculate the sum of all product ids consisting in the same subsequence
     * repeated more than once (e.g 123123, 565656, etc)
     */
    public static long sumAllMultiRepeatedSequenceIds(List<ProductIdRange> productIdRanges) {
        return productIdRanges.stream()
                .flatMapToLong(range -> LongStream.rangeClosed(range.getInitialId(), range.getFinalId()))
                .filter(InvalidProductIdProcesser::hasRepeatedSequence)
                .sum();
    }

    /**
     * Helper: Checks if a number is composed of two or more identical subsequences
     */
    private static boolean hasRepeatedSequence(long number) {
        String s = String.valueOf(number);
        int len = s.length();

        return IntStream.rangeClosed(1, len / 2)
                .filter(chunkSize -> len % chunkSize == 0)
                .anyMatch(chunkSize -> isPatternRepeated(s, chunkSize));
    }

    /**
     * Helper: Checks if the string 's' is made entirely of the pattern found in the
     * first 'chunkSize'
     */
    private static boolean isPatternRepeated(String s, int chunkSize) {
        String pattern = s.substring(0, chunkSize);
        int len = s.length();

        return IntStream.iterate(chunkSize, i -> i < len, i -> i + chunkSize)
                .allMatch(i -> s.startsWith(pattern, i));
    }
}
