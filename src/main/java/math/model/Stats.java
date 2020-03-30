package math.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Stats {
    private Map<String, Integer> scores = new HashMap<>();

    public void incrementScore(String key) {
        int score = getScore(key);
        if (score < 9) {
            score++;
        }
        scores.put(key, score);
    }

    public void decrementScore(String key) {
        int score = getScore(key);
        if (score > 0) {
            score--;
        }
        scores.put(key, score);
    }

    public int getScore(String key) {
        Integer value = scores.get(key);
        return value == null ? 0 : value;
    }

    public void setScore(String key, int value) {
        scores.put(key, value);
    }
}
