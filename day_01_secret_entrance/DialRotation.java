package day_1_secret_entrance;

public class DialRotation {
    private final Direction direction;
    private final int clicks;

    public DialRotation(Direction direction, int clicks) {
        this.direction = direction;
        this.clicks = clicks;
    }

    public static DialRotation fromLine(String line) {
        char dirChar = line.charAt(0);
        int clicks = Integer.parseInt(line.substring(1));
        
        Direction direction = (dirChar == 'R') ? Direction.RIGHT : Direction.LEFT;

        return new DialRotation(direction, clicks);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getClicks() {
        return this.clicks;
    }
}
