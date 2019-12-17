package math;

import lombok.Data;

@Data
public class Stats {
    private int[] errors = new int[100];
    private int[] oks = new int[100];

    public void updateStats(int a, int b, boolean ok) {
        int i = a * b;
        if (ok) {
            oks[i]++;
        } else {
            errors[i]++;
        }
    }

    public double getValue(int a, int b) {
        int i = a * b;
        double total = oks[i] + errors[i];
        return oks[i] / total;
    }
}
