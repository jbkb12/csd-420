/*
 * Justin Bradley
 * CSD 420 Module 5.2 Programming Assignment
 * November 15, 2026
 *
 * Reads words from collection_of_words.txt and displays each word
 * one time, first in ascending order and then in descending order.
 * A TreeSet is used because it drops duplicate words and keeps the
 * words sorted automatically.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class WordSorter {

    // Reads every word from the file and returns them in a TreeSet.
    // Words are made lowercase so "Java" and "java" count as one word.
    public static TreeSet<String> getUniqueWords(String fileName) throws FileNotFoundException {
        TreeSet<String> words = new TreeSet<>();
        Scanner input = new Scanner(new File(fileName));

        while (input.hasNext()) {
            words.add(input.next().toLowerCase());
        }

        input.close();
        return words;
    }

    public static void main(String[] args) {
        try {
            TreeSet<String> words = getUniqueWords("collection_of_words.txt");

            // A TreeSet is already in ascending order
            System.out.println("Ascending order:");
            for (String word : words) {
                System.out.println(word);
            }

            // descendingSet() gives the same words in reverse order
            System.out.println("\nDescending order:");
            for (String word : words.descendingSet()) {
                System.out.println(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find collection_of_words.txt");
        }
    }
}
