package ga.util;

import java.util.Random;

public class Number {
    /* https://stackoverflow.com/a/3680648/10805602 */
    public static double random(double start, double end) {
        Random r = new Random();
        return start + (end - start) * r.nextDouble();
    }

    public static double toPercent(double rawProb) {
        return rawProb * 100;
    }
}
