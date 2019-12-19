package math.model;

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

	public Double getScore(int test) {
		return getAvgScore(String.valueOf(test), 5);
	}

	public Double getScore(int test, int argument) {
		return getAvgScore(argument + "x" + test, 5);
	}

	private Double getAvgScore(String key, int limit) {
		List<Double> list = scores.get(key);
		if (list == null) {
			return null;
		} else {
			return list.stream().limit(limit).mapToDouble(it -> it).average().getAsDouble();
		}
	}
}
