package day_3_lobby;

import java.util.Collections;
import java.util.List;

public class BatteryBank {
    private final List<Integer> batteryJoltages;

    public BatteryBank(List<Integer> batteryJoltages) {
        this.batteryJoltages = batteryJoltages;
    }

    public static BatteryBank fromLine(String line) {
        List<Integer> batteries = line
                .chars()
                .map(Character::getNumericValue)
                .boxed()
                .toList();
        return new BatteryBank(batteries);
    }

    /**
     * Calculate the biggest number of 'batteryNumberToActivate' digits that is a
     * subsequence of battery joltages sequence.
     */
    public long getLargestPossibleJoltage(int batteryNumberToActivate) {
        List<Integer> updatedBatteryJoltages = this.batteryJoltages;
        StringBuilder batteriesToActivate = new StringBuilder();
        int indexOfMaxJoltage = -1;

        for (int i = 0; i < batteryNumberToActivate; i++) {
            updatedBatteryJoltages = updatedBatteryJoltages.subList(indexOfMaxJoltage + 1,
                    updatedBatteryJoltages.size());
            int maxBatteryJoltage = Collections.max(updatedBatteryJoltages.subList(
                    0, updatedBatteryJoltages.size() - batteryNumberToActivate + i + 1));
            indexOfMaxJoltage = updatedBatteryJoltages.indexOf(maxBatteryJoltage);

            batteriesToActivate.append(maxBatteryJoltage);
        }

        return Long.parseLong(batteriesToActivate.toString());
    }
}
