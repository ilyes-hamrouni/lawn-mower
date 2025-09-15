package libon.mower.modals.enums;


import java.util.Arrays;

public enum Instruction {
    G, D, A;

    public static Instruction from(String s) {
        if (s == null || s.isEmpty()) throw new IllegalArgumentException("instruction cannot be null/empty");
        return Arrays.stream(values())
                .filter(i -> i.name().equalsIgnoreCase(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown instruction: " + s));
    }
}
