/*
 * Author: Justin Bradley
 * Course: CSD 420 Advanced Java Programming
 * Assignment: Module 2.2 Programming Assignment
 * Date: November 1, 2026
 *
 * Purpose:
 *   Generates an array of five random integers and an array of five random
 *   double values, then writes both arrays to a binary file named
 *   "Bradley datafile.dat". If the file does not exist, it is created.
 *   If it already exists, the new data is appended to the end of the file.
 *
 * Attribution:
 *   Binary I/O approach (DataOutputStream wrapped around FileOutputStream)
 *   based on Liang, Introduction to Java Programming and Data Structures,
 *   12th Edition, Chapter 17 (Binary I/O).
 *   FileOutputStream append constructor documented at:
 *   https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/FileOutputStream.html
 */

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class WriteDataFile {

    // Shared settings, also used by ReadDataFile so the two programs stay in sync.
    public static final String FILE_NAME = "Bradley datafile.dat";
    public static final int ARRAY_SIZE = 5;

    private static final int MAX_INT = 100;
    private static final double MAX_DOUBLE = 100.0;

    public static void main(String[] args) {
        Random random = new Random();

        int[] intValues = createRandomInts(random);
        double[] doubleValues = createRandomDoubles(random);

        System.out.println("Integers to write: " + Arrays.toString(intValues));
        System.out.println("Doubles to write:  " + Arrays.toString(doubleValues));

        writeData(intValues, doubleValues);
    }

    /** Returns an array of random integers from 0 to MAX_INT - 1. */
    private static int[] createRandomInts(Random random) {
        int[] values = new int[ARRAY_SIZE];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(MAX_INT);
        }
        return values;
    }

    /** Returns an array of random doubles from 0.0 up to (not including) MAX_DOUBLE. */
    private static double[] createRandomDoubles(Random random) {
        double[] values = new double[ARRAY_SIZE];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextDouble() * MAX_DOUBLE;
        }
        return values;
    }

    /**
     * Writes the integers followed by the doubles to the data file.
     * Passing true to FileOutputStream creates the file if it is missing
     * and appends to it if it already exists.
     */
    private static void writeData(int[] intValues, double[] doubleValues) {
        try (DataOutputStream output = new DataOutputStream(
                new FileOutputStream(FILE_NAME, true))) {

            for (int value : intValues) {
                output.writeInt(value);
            }
            for (double value : doubleValues) {
                output.writeDouble(value);
            }

            System.out.println("Data was successfully written to \"" + FILE_NAME + "\".");
        } catch (IOException e) {
            System.err.println("Error: Unable to write to \"" + FILE_NAME + "\". " + e.getMessage());
        }
    }
}
