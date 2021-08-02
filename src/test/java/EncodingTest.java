import ga.util.Encoding;
import ga.util.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ga.util.Encoding.*;

public class EncodingTest {

    @Test
    public void general() {
        double[] input = new double[]{0.6, 0.65, 1.4, -0.342, 0.54};

        int precision = 3;
        double start = -1;
        double end = 2;
        int LENGTH = (int) Math.ceil(log2(Math.pow(10, precision) * (end - start)));

        short[] bitmap = Encoding.toBitMapVector(input, start, end, precision);

        double[] reconverted = Encoding.toDoubleVector(bitmap, start, end, precision);

        Vector.print(input);
        Vector.print(reconverted);
    }

    @Test
    public void grayToBinaryTest() {
        int grayNumber = 819; // 1100110011
        int expected = 546;

        Assertions.assertEquals(expected, Encoding.grayToBinary(grayNumber));
    }

    @Test
    public void binaryToIntTest() {
        int expected = 3276; // 110011001100

        short[] binary = new short[]{0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1}; // 110011001100

        Assertions.assertEquals(expected, Encoding.bitmapToInt(binary));
    }

    @Test
    public void toGrayTest() {
        int binary = 654; // 1010001110
        int result = toGray(binary);

        int expected = 969; // 1111001001

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void binaryToMapTest() {
        int binary = 654; // 1010001110

        short[] value = Encoding.binaryToMap(binary, 10);

        Vector.printBinary(value);
    }

    @Test
    public void mappedToDoubleAndBinary() {

        int precision = 3;

        double start = -1;
        double end = 2;
        int LENGTH = (int) Math.ceil(log2(Math.pow(10, precision) * (end - start)));

        double value = 0.123;

        int binary = Encoding.doubleMappedToBinary(start, value, end, precision);

        double number = Encoding.binaryMappedToDouble(binary, start, end, LENGTH);

        Assertions.assertEquals(value, number);
    }

}
