package ga.util;

public class Encoding {
    /* BINARY */
    public static int doubleMappedToBinary(double start, double value, double end, int precision) {
        double interval = end - start;

        /* the number of bits necessary to store the max number */
        int LENGTH = calculateBitMapNumberLength(interval, precision);

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

    public static int calculateBitMapNumberLength(double interval, int precision) {
        return (int) Math.ceil(log2(Math.pow(10, precision) * interval));
    }

    /* GRAY */
    public static int toGray(int n) {
        return n ^ (n >> 1);
    }

    public static int grayToBinary(int grayCode) {
        int n = 0;

        while (grayCode != 0) {
            n ^= grayCode;
            grayCode >>= 1;
        }

        return n;
    }

    /* MAPPING */
    public static short[] binaryToMap(int number, int LENGTH) {
        short[] map = new short[LENGTH];

        int i = 0;
        while (number != 0) {
            map[i++] = (short) (number % 2 == 1 ? 1 : 0);
            number >>= 1;
        }

        return map;
    }

    public static int bitmapToInt(short[] map) {
        int n = 0;

        for (int i = map.length - 1; i >= 0; i--) {
            n += map[i];
            n <<= 1;
        }

        n >>= 1;

        return n;
    }

    /* VECTOR TO BITMAP <-> BITMAP TO VECTOR */
    public static short[] toBitMapVector(double[] value, double start, double end, int precision) {
        int LENGTH = calculateBitMapNumberLength(end - start, precision);

        int n = value.length;

        short[] bitmap = new short[n * LENGTH];

        for (int i = 0; i < n; i++) {
            int binary = doubleMappedToBinary(start, value[i], end, precision);
            int grayEncoding = toGray(binary);

            short[] bitmapValue = binaryToMap(grayEncoding, LENGTH);

            Vector.copyInto(bitmap, bitmapValue, i * LENGTH, LENGTH);
        }

        return bitmap;
    }

    public static double[] toDoubleVector(short[] bitmap, double start, double end, int precision) {
        int LENGTH = calculateBitMapNumberLength(end - start, precision);

        int n = bitmap.length / LENGTH;

        double[] values = new double[n];

        for (int i = 0; i < n; i++) {
            short[] numberBitMap = Vector.getSubVector(bitmap, i * LENGTH, LENGTH);

            int grayBinary = bitmapToInt(numberBitMap);
            int binary = grayToBinary(grayBinary);

            values[i] = binaryMappedToDouble(binary, start, end, LENGTH);
        }

        return values;
    }

    /* MATH STUFF */
    public static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    /* PRINT STUFF */
    public static void printBitmapVector(short[] bitmap, int LENGTH) {
        int n = bitmap.length;
        for (int i = 0; i < n; i++) {
            System.out.printf("%d", bitmap[i]);

            if ((i + 1) % LENGTH == 0) {
                System.out.print(" ");
            }
        }

        System.out.println("");
    }
}