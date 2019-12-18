package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Stats {
	private Map<String,List<Double>> scores = new HashMap<>();

	public void addScore(String key, double score) {
        List<Double> list = scores.computeIfAbsent(key, k -> new ArrayList<>());
        list.add(0, score);
	}

	public double getAvgScore(String key, int limit) {
        List<Double> list = scores.get(key);
        if (list == null) {
            return 0;
        } else {
            return list.stream().limit(limit).mapToDouble(it -> it).average().orElse(0);
        }
	}
}
