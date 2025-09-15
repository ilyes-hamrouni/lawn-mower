package libon.mower.modals.enums;


import java.util.Arrays;


public enum Orientation {
    N, E, S, W;

    public static Orientation from(String s) {
        if (s == null) throw new IllegalArgumentException("orientation cannot be null");
        return Arrays.stream(values())
                .filter(o -> o.name().equalsIgnoreCase(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown orientation: " + s));
    }

    public Orientation turnLeft() {
        return switch (this) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
        };
    }

    public Orientation turnRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }
}
