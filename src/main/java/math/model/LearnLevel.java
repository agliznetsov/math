package math.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LearnLevel {
    L0(0, 0, 1, 1),
    L1(1, 2, 3, 2),
    L2(2, 4, 6, 4),
    L3(3, 7, 9, 0);

    private final int level;
    private final int startStep;
    private final int endStep;
    private final int answerCount;
}
