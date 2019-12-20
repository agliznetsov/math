package math.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Predicate;

import lombok.Data;

@Data
public class Stats {
	private Map<String,List<Double>> scores = new HashMap<>();

	public void addScore(String key, double score) {
		List<Double> list = scores.computeIfAbsent(key, k -> new ArrayList<>());
		list.add(0, score);
	}

	public Double getAvgScore(Predicate<String> key, int limit) {
		OptionalDouble res = scores.entrySet().stream()
				.filter(e -> key.test(e.getKey()))
				.mapToDouble(e -> getAvgScore(e.getValue(), limit))
				.average();
		return res.isPresent() ? res.getAsDouble() : null;
	}

	public Double getAvgScore(String key, int limit) {
		List<Double> list = scores.get(key);
		if (list == null) {
			return null;
		} else {
			return getAvgScore(list, limit);
		}
	}

	private Double getAvgScore(List<Double> list, int limit) {
		return list.stream().limit(limit).mapToDouble(it -> it).average().getAsDouble();
	}
}
