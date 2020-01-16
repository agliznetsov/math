package math.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Operation {
    MULTIPLICATION("x", "\u00D7"),
    DIVISION("/", "\u00F7");

    private final String key;
    private final String label;

    @Override
    public String toString() {
        return label;
    }

    public String key() {
        return key;
    }

    public String question(int a, int b) {
        if (this == MULTIPLICATION) {
            return a + " " + label + " " + b;
        } else {
            return (a * b) + " " + label + " " + b;
        }
    }

    public int answer(int a, int b) {
        if (this == MULTIPLICATION) {
            return a * b;
        } else {
            return a;
        }
    }
}
