package day_10_factory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Machine {
    final int lightTarget;
    final int[] joltageTarget;
    final List<Button> buttons;

    private Machine(int lightTarget, int[] joltageTarget, List<Button> buttons) {
        this.lightTarget = lightTarget;
        this.joltageTarget = joltageTarget;
        this.buttons = buttons;
    }

    public static Machine parse(String line) {
        // Lights
        String lights = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
        int lightTarget = 0;
        for (int i = 0; i < lights.length(); i++) {
            if (lights.charAt(i) == '#') {
                lightTarget |= (1 << i);
            }
        }

        // Joltage
        String joltageStr = line.substring(line.indexOf('{') + 1, line.indexOf('}'));
        String[] joltageParts = joltageStr.split(",");
        int[] joltageTarget = new int[joltageParts.length];
        for (int i = 0; i < joltageParts.length; i++) {
            joltageTarget[i] = Integer.parseInt(joltageParts[i].trim());
        }

        // Buttons
        List<Button> buttons = new ArrayList<>();
        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(line);
        while (m.find()) {
            String[] indices = m.group(1).split(",");
            int bitMask = 0;
            int[] counters = new int[joltageTarget.length];
            for (String s : indices) {
                int idx = Integer.parseInt(s.trim());
                bitMask |= (1 << idx);
                counters[idx] = 1;
            }
            buttons.add(new Button(bitMask, counters));
        }

        return new Machine(lightTarget, joltageTarget, buttons);
    }
}
