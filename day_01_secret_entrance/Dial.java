package day_1_secret_entrance;

import java.util.List;

public class Dial {

    private final int size;
    private int initialPosition;

    public Dial(int dialSize, int initialPosition) throws IllegalArgumentException {
        if (dialSize <= initialPosition) {
            throw new IllegalArgumentException("Initial position of Dial must be lower than its size.");
        }
        this.size = dialSize;
        this.initialPosition = initialPosition;
    }

    private int calculateNewPosition(int currentPosition, DialRotation rotation) {
        if (rotation.getDirection() == Direction.RIGHT) {
            return (currentPosition + rotation.getClicks()) % this.size;
        } else {
            int newPosition = currentPosition - (rotation.getClicks() % this.size);
            return newPosition >= 0 ? newPosition : newPosition + this.size;
        }
    }

    /**
     * Counts how many times the dial points to zero at the end of a rotation.
     */
    public int getEndingZeroCounter(List<DialRotation> rotations) {
        int currentPosition = this.initialPosition;
        int counter = 0;
        for (DialRotation rotation : rotations) {
            currentPosition = this.calculateNewPosition(currentPosition, rotation);
            if (currentPosition == 0) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Calculates how many times the dial has been pointing to zero in an only
     * rotation.
     */
    private int countPassesOfZero(int currentPosition, DialRotation rotation) {

        // Right
        if (rotation.getDirection() == Direction.RIGHT)
            return (currentPosition + rotation.getClicks()) / this.size;

        // Left
        int distanceToZero = (this.size - currentPosition) % this.size;
        return (distanceToZero + rotation.getClicks()) / this.size;

    }

    /**
     * Counts how many times the dial points to the given position at any time
     * (ending or during a rotation).
     */
    public int getAnytimeZeroCounter(List<DialRotation> rotations) {
        int currentPosition = this.initialPosition;
        int counter = 0;
        for (DialRotation rotation : rotations) {
            counter += countPassesOfZero(currentPosition, rotation);
            currentPosition = this.calculateNewPosition(currentPosition, rotation);
        }
        return counter;
    }

}
