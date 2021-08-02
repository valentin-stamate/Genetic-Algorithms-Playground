package implementation;

import ga.util.Encoding;
import ga.util.Number;
import ga.util.Vector;

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
        return functionDimensions * calculateBitPointLength(max() - min(), precision);
    }

    /* BIT MANIPULATION */
    /** Returns the bitmap length necessary to store a point  */
    public static int calculateBitPointLength(double interval, int precision) {
        return (int) Math.ceil(Number.log2(Math.pow(10, precision) * interval));
    }

    /* BINARY */
    public static int doubleMappedToBinary(double start, double value, double end, int precision) {
        double interval = end - start;

        /* the number of bits necessary to store the max number */
        int LENGTH = calculateBitPointLength(interval, precision);

        /* the max value that can be represented */
        int MAX = (1 << LENGTH) - 1;

        /* the value to be mapped */
        double x = value - start;

        return (int) (x * (1.0 * MAX / interval));
    }

    public static double binaryMappedToDouble(int binary, double start, double end, int LENGTH) {
        int MAX = (1 << LENGTH) - 1;
        double interval = end - start;

        return 1.0 * binary * (interval / MAX) + start;
    }

    /* VECTOR TO BITMAP <-> BITMAP TO VECTOR */
    public static short[] toBitMapVector(double[] value, double start, double end, int precision) {
        int LENGTH = calculateBitPointLength(end - start, precision);

        int n = value.length;

        short[] bitmap = new short[n * LENGTH];

        for (int i = 0; i < n; i++) {
            int binary = doubleMappedToBinary(start, value[i], end, precision);
            int grayEncoding = Encoding.toGray(binary);

            short[] bitmapValue = Encoding.binaryToMap(grayEncoding, LENGTH);

            Vector.copyInto(bitmap, bitmapValue, i * LENGTH, LENGTH);
        }

        return bitmap;
    }

    public static double[] toDoubleVector(short[] bitmap, double start, double end, int precision) {
        int LENGTH = calculateBitPointLength(end - start, precision);

        int n = bitmap.length / LENGTH;

        double[] values = new double[n];

        for (int i = 0; i < n; i++) {
            short[] numberBitMap = Vector.getSubVector(bitmap, i * LENGTH, LENGTH);

            int grayBinary = Encoding.bitmapToInt(numberBitMap);
            int binary = Encoding.grayToBinary(grayBinary);

            values[i] = binaryMappedToDouble(binary, start, end, LENGTH);
        }

        return values;
    }

}
