package math.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestViewTest {
	@Test
	public void formatTime() {
		assertEquals("1:06", TestView.formatTime(66_000));
	}
}
