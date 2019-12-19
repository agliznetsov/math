package math;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import math.model.Settings;

public class SettingsTest {
	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testSerialization() throws Exception {
		String json = objectMapper.writeValueAsString(new Settings());
		Settings settings = objectMapper.readValue(json, Settings.class);
		assertNotNull(settings);
	}
}
