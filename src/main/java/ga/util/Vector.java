package ga.util;

public class Vector {
    public static void copyInto(short[] bitmap, short[] binary, int start, int length) {
        for (int i = start; i < start + length; i++) {
            bitmap[i] = binary[i - start];
        }
    }

    public static short[] getSubVector(short[] bitmap, int start, int length) {
        short[] vector = new short[length];

        for (int i = start; i < start + length; i++) {
            vector[i - start] = bitmap[i];
        }

        return vector;
    }

    public static void print(double[] vector) {
        System.out.print("| ");
        for (double x : vector) {
            System.out.printf("%6.3f ", x);
        }
        System.out.print("|");

        System.out.println("");
    }

    public static void printBinary(short[] vector) {
        System.out.print("|");
        for (int i = vector.length - 1; i >= 0; i--) {
            System.out.printf("%d", vector[i]);
        }
        System.out.print("|");

        System.out.println("");
    }

    public static short[] copyOf(short[] gene) {
        short[] copy = new short[gene.length];
        System.arraycopy(gene, 0, copy, 0, gene.length);
        return copy;
    }
}
