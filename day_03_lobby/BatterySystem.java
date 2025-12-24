package day_3_lobby;

import java.util.List;

public class BatterySystem {
    public final List<BatteryBank> batteryBanks;

    public BatterySystem(List<BatteryBank> batteryBanks) {
        this.batteryBanks = batteryBanks;
    }

    /**
     * Calculate the sum of the largest possible joltage from every battery bank.
     */
    public long getLargestPossibleJoltage(int batteriesToTurnOn) {
        return batteryBanks
                .stream()
                .map(bank -> bank.getLargestPossibleJoltage(batteriesToTurnOn))
                .reduce(0L, (subtotal, joltage) -> subtotal + joltage);
    }
}
