package day_5_cafeteria;

import java.util.Arrays;
import java.util.stream.LongStream;

/**
 * Inclusive range
 */
public class IngredientIdRange {
    private final long startId;
    private final long endId;

    public IngredientIdRange(long startId, long endId) throws IllegalArgumentException {
        if (startId > endId)
            throw new IllegalArgumentException("Start ID must not be higher than end ID.");
        this.startId = startId;
        this.endId = endId;
    }

    public static IngredientIdRange fromLine(String line) {
        long[] rangeLimits = Arrays
                .stream(line.split("-"))
                .mapToLong(Long::parseLong)
                .toArray();
        return new IngredientIdRange(rangeLimits[0], rangeLimits[1]);
    }

    public boolean includes(long ingredientId) {
        return (this.startId <= ingredientId && ingredientId <= this.endId);
    }

    public long[] toArray() {
        return LongStream.rangeClosed(this.startId, this.endId).toArray();
    }

    // Getters

    public long getStartId() {
        return this.startId;
    }

    public long getEndId() {
        return this.endId;
    }
}
