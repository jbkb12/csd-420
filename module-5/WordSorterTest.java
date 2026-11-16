/*
 * Justin Bradley
 * CSD 420 Module 5.2 Programming Assignment
 * November 15, 2026
 *
 * Test program for WordSorter. Each test prints PASS or FAIL.
 * collection_of_words.txt has 18 words, but only 14 are unique
 * because java, set, list, and queue are repeated on purpose.
 */

import java.io.FileNotFoundException;
import java.util.TreeSet;

public class WordSorterTest {

    // Prints PASS if the condition is true, otherwise FAIL
    public static void check(String testName, boolean passed) {
        if (passed) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
        }
    }

    public static void main(String[] args) {
        try {
            TreeSet<String> words = WordSorter.getUniqueWords("collection_of_words.txt");

            // Test 1: duplicates removed, 14 unique words remain
            check("14 unique words", words.size() == 14);

            // Test 2: first word in ascending order
            check("first word is array", words.first().equals("array"));

            // Test 3: last word in ascending order
            check("last word is tree", words.last().equals("tree"));

            // Test 4: descending order starts with the last word
            check("descending starts with tree", words.descendingSet().first().equals("tree"));

            // Test 5: a repeated word is still in the set
            check("java is in the set", words.contains("java"));

        } catch (FileNotFoundException e) {
            System.out.println("FAIL: could not find collection_of_words.txt");
        }
    }
}
