package ga.util;

public class Encoding {

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
            n += (map[i] == 1 ? 1 : 0); // I know, I could've done: n += map[i];
            n <<= 1;
        }

        n >>= 1;

        return n;
    }

    /* PRINT STUFF */
    public static void printBitmapVector(boolean[] bitmap, int LENGTH) {
        int n = bitmap.length;
        for (int i = 0; i < n; i++) {
            System.out.printf("%d", bitmap[i] ? 1 : 0);

            if ((i + 1) % LENGTH == 0) {
                System.out.print(" ");
            }
        }

        System.out.println("");
    }
}