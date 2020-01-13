package math.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Multiplier {
    M0(0),
    M1(1),
    M2(2),
    M3(3),
    M4(4),
    M5(5),
    M6(5),
    M7(7),
    M8(8),
    M9(9),
    All(null);

    private final Integer value;

    @Override
    public String toString() {
        return value == null ? "?" : String.valueOf(value);
    }
}
