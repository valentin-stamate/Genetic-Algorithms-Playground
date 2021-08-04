package ga.util;

import java.util.Random;

public class Number {
    /** Generates a random number for a given range
     * @param start is the starting value
     * @param end   is the end value <p>
     * @see       "https://stackoverflow.com/a/3680648/10805602" */
    public static double random(double start, double end) {
        Random r = new Random();
        return start + (end - start) * r.nextDouble();
    }

    public static double toPercent(double rawProb) {
        return rawProb * 100;
    }

    /* MATH STUFF */
    public static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }
}
