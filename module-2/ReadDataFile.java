/*
 * Author: Justin Bradley
 * Course: CSD 420 Advanced Java Programming
 * Assignment: Module 2.2 Programming Assignment
 * Date: November 1, 2026
 *
 * Purpose:
 *   Reads the binary file "Bradley datafile.dat" created by WriteDataFile and
 *   displays its contents. Each time WriteDataFile runs, it appends one record
 *   (five integers followed by five doubles), so this program reads and
 *   displays one record at a time until it reaches the end of the file.
 *
 * Attribution:
 *   Binary I/O approach (DataInputStream wrapped around FileInputStream) and
 *   use of EOFException to detect the end of the file based on Liang,
 *   Introduction to Java Programming and Data Structures, 12th Edition,
 *   Chapter 17 (Binary I/O).
 */

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ReadDataFile {

    public static void main(String[] args) {
        String fileName = WriteDataFile.FILE_NAME;
        int size = WriteDataFile.ARRAY_SIZE;

        try (DataInputStream input = new DataInputStream(
                new FileInputStream(fileName))) {

            System.out.println("Contents of \"" + fileName + "\":");
            int recordNumber = 0;

            // Read one full record at a time until EOFException signals the end.
            while (true) {
                int[] intValues = new int[size];
                double[] doubleValues = new double[size];

                try {
                    for (int i = 0; i < size; i++) {
                        intValues[i] = input.readInt();
                    }
                    for (int i = 0; i < size; i++) {
                        doubleValues[i] = input.readDouble();
                    }
                } catch (EOFException e) {
                    break; // Normal end of file, no more records to read.
                }

                recordNumber++;
                System.out.println("Record " + recordNumber + ":");
                System.out.println("  Integers: " + Arrays.toString(intValues));
                System.out.println("  Doubles:  " + Arrays.toString(doubleValues));
            }

            if (recordNumber == 0) {
                System.out.println("The file is empty.");
            } else {
                System.out.println("Total records read: " + recordNumber);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file \"" + fileName
                    + "\" was not found. Run WriteDataFile first.");
        } catch (IOException e) {
            System.err.println("Error: Unable to read \"" + fileName + "\". " + e.getMessage());
        }
    }
}
