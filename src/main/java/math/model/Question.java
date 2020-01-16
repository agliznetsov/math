package math.model;

public class Question {
    public final Operation op;
    public final int a;
    public final int b;

    public Question(Operation op, int a, int b) {
        this.op = op;
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return op.question(a, b);
    }

    public int answer() {
        return op.answer(a, b);
    }

    public String key() {
        return a + op.key() + b;
    }
}
