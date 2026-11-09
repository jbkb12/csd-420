/*
 * Name:       Justin Bradley
 * Date:       November 8, 2026
 * Course:     CSD 420 Advanced Java Programming
 * Assignment: Module 3.2 Programming Assignment
 *
 * Purpose:
 * This program fills an ArrayList with 50 random whole numbers from 1 to 20.
 * It then calls a generic static method, removeDuplicates, which returns a
 * NEW ArrayList holding each value only once. The original list is left
 * unchanged so both lists can be printed and compared.
 *
 * How to run:
 *   javac RemoveDuplicatesTest.java
 *   java RemoveDuplicatesTest
 */

import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicatesTest {

    public static void main(String[] args) {

        // Create the original list and a random number generator.
        ArrayList<Integer> original = new ArrayList<>();
        Random rand = new Random();

        // Fill the original list with 50 random values from 1 to 20.
        // nextInt(20) returns 0 to 19, so adding 1 gives 1 to 20.
        for (int i = 0; i < 50; i++) {
            original.add(rand.nextInt(20) + 1);
        }

        // Call the generic method to build a new list with no duplicates.
        ArrayList<Integer> noDuplicates = removeDuplicates(original);

        // Display both lists and their sizes so the results can be checked.
        System.out.println("Original list (" + original.size() + " values):");
        System.out.println(original);
        System.out.println();
        System.out.println("List with duplicates removed (" + noDuplicates.size() + " values):");
        System.out.println(noDuplicates);
        System.out.println();

        // Quick check: the original list should still have all 50 values.
        System.out.println("Original list still has " + original.size()
                + " values, so it was not changed.");
    }

    /**
     * Returns a new ArrayList that contains every value from the given list,
     * with duplicates removed. Values stay in the order they first appear.
     *
     * The method is generic (<E>), so it works for a list of any type,
     * such as Integer or String.
     *
     * @param list the original list, which is not modified
     * @param <E>  the type of elements in the list
     * @return a new ArrayList with each value from the original list only once
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        // Start with an empty list to hold the unique values.
        ArrayList<E> result = new ArrayList<>();

        // Look at each value in the original list.
        for (E item : list) {
            // Only add the value if it is not already in the new list.
            if (!result.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }
}
