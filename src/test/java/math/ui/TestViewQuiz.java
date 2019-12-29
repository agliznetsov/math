package math.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestViewQuiz {
	@Test
	public void formatTime() {
		assertEquals("1:06", QuizView.formatTime(66_000));
	}
}
