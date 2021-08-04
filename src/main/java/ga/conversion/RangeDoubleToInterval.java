package ga.conversion;

import ga.util.Encoding;
import ga.util.Number;
import ga.util.Vector;

/** Utility class to convert a double representation to binary and vice versa. */
public class RangeDoubleToInterval {
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
    /** Converts a double representation to a bitmap gene
     * @param value the original representation
     * @param start  the minimum value among all points from the original representation
     * @param end    the maximum value among all points from the original representation
     * @param precision the precision */
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

    /** Converts a gene to it's double vector representation
     * @param bitmap the gene
     * @param start  the minimum value among all points from the original representation
     * @param end    the maximum value among all points from the original representation
     * @param precision the precision */
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
