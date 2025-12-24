package day_10_factory;

public class Button {
    final int bitMask;
    final int[] counters;

    public Button(int bitMask, int[] counters) {
        this.bitMask = bitMask;
        this.counters = counters;
    }
}
