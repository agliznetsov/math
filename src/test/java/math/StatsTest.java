package math;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import math.model.Stats;

public class StatsTest {
	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testSerialization() throws Exception {
		String json = objectMapper.writeValueAsString(new Stats());
		Stats stats = objectMapper.readValue(json, Stats.class);
		assertNotNull(stats);
	}
}
