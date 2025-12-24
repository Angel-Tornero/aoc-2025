package day_2_gift_shop;

public class ProductIdRange {
    private final long initialId;
    private final long finalId;

    public ProductIdRange(long initialId, long finalId) throws IllegalArgumentException {
        if (initialId > finalId) {
            throw new IllegalArgumentException("Initial ID must not be higher than final ID.");
        }
        this.initialId = initialId;
        this.finalId = finalId;
    }

    static public ProductIdRange fromLine(String line) {
        String[] splitString = line.split("-");
        long initialId = Long.parseLong(splitString[0]);
        long finalId = Long.parseLong(splitString[1]);
        return new ProductIdRange(initialId, finalId);
    }

    // Getters and setters

    public long getInitialId() {
        return this.initialId;
    }

    public long getFinalId() {
        return this.finalId;
    }

}
