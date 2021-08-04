package implementation;

import ga.conversion.RangeDoubleToInterval;

/* Rastrigin's Function 6 */
/* http://www.geatbx.com/docu/fcnindex-01.html#P140_6155 */
public class Function {
    private static final double FUN_MIN = -5.12;
    private static final double FUN_MAX = 5.12;

    private final int functionDimensions;
    private final int precision;

    public Function(int components, int precision) {
        this.functionDimensions = components;
        this.precision = precision;
    }

    public double fun(double[] X) {
        int n = X.length;

        if (n != functionDimensions) {
            System.out.println("Fun: Something went wrong");
            return 0;
        }

        double sum = 10 * n;

        for (int i = 0; i < n; i++) {
            sum += (X[i] * X[i] - 10 * Math.cos(2 * Math.PI * X[i]));
        }

        return sum;
    }

    public int components() {
        return functionDimensions;
    }

    public double min() {
        return FUN_MIN;
    }

    public double max() {
        return FUN_MAX;
    }

    public int getPrecision() {
        return precision;
    }

    public double getInterval() {
        return max() - min();
    }

    public String getName() {
        return "Rastrigin's Function 6";
    }

    public int calculateGeneLength() {
        return functionDimensions * RangeDoubleToInterval.calculateBitPointLength(max() - min(), precision);
    }

}
