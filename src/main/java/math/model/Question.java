package math.model;

import java.util.function.Predicate;

public class Question {
	public final int a;
	public final int b;

	public Question(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public static Question parseKey(String key) {
		int i = key.indexOf('x');
		return new Question(Integer.parseInt(key.substring(0, i)), Integer.parseInt(key.substring(i + 1)));
	}

	public static Predicate<String> predicate(int multiplier) {
		return (key) -> Question.parseKey(key).b == multiplier;
	}

	@Override
	public String toString() {
		return a + " x " + b;
	}

	public int answer() {
		return a * b;
	}

	public String key() {
		return a + "x" + b;
	}
}
