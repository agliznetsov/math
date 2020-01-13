package math.model;

import lombok.Data;

@Data
public class Settings {
    String multiplier = Multiplier.M0.name();
    String learnLevel = LearnLevel.L0.name();
    Integer time = 30;
}
