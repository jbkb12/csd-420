/*
 * Name:       Justin Bradley
 * Date:       November 8, 2026
 * Course:     CSD 420 Advanced Java Programming
 * Assignment: Module 4.2 Programming Assignment
 *
 * Purpose:
 * This program stores integers in a LinkedList and times two ways of
 * traversing it:
 *   1. Using an Iterator
 *   2. Using the get(index) method inside a regular for loop
 * It runs the test first with 50,000 integers and then with 500,000 integers.
 * It also checks that both traversals read every value correctly.
 *
 * How to run:
 *   javac LinkedListTraversalTest.java
 *   java LinkedListTraversalTest
 *
 * Note: the 500,000 get(index) test is slow on purpose. It can take a minute
 * or two to finish. That slowness is the point of the assignment.
 *
 * RESULTS DISCUSSION:
 * Results from my test run:
 *
 *   List size    Iterator    get(index)
 *   50,000       2 ms        649 ms
 *   500,000      5 ms        78,733 ms (about 1 minute 19 seconds)
 *
 * The iterator stayed fast at both sizes. A LinkedList is a chain of nodes,
 * and the iterator remembers which node it is on. Each call to next() just
 * steps to the following node, so the whole list is read in one pass. The
 * work grows in a straight line with the size of the list: 10 times more
 * data means about 10 times more work, which is still only milliseconds.
 *
 * The get(index) approach was much slower, and it got worse very quickly.
 * A LinkedList has no way to jump straight to position i like an array can.
 * Every get(i) call starts at the front (or the back) of the list and walks
 * node by node until it reaches i. Inside a for loop, that walk is repeated
 * for every single index, so the total work grows with the size squared.
 *
 * That is why going from 50,000 to 500,000 integers (10 times more data)
 * made get(index) about 121 times slower, close to the 100 times the
 * size-squared rule predicts, while the iterator time barely changed.
 * At 500,000 integers, get(index) took over 15,000 times longer than the
 * iterator to read exactly the same values.
 *
 * Conclusion: to traverse a LinkedList, use an iterator or a foreach loop
 * (which uses an iterator behind the scenes). Save get(index) for an
 * ArrayList, where looking up a position is instant.
 */

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTraversalTest {

    public static void main(String[] args) {

        // Run the full test for each list size the assignment asks for.
        runTest(50000);
        runTest(500000);
    }

    /**
     * Builds a LinkedList with the given number of integers, then times
     * the iterator traversal and the get(index) traversal.
     *
     * @param size how many integers to store in the list
     */
    public static void runTest(int size) {

        System.out.println("===== Testing with " + size + " integers =====");

        // Fill the list with the numbers 0, 1, 2, ... size - 1.
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // The sum of 0 to (size - 1) is size * (size - 1) / 2.
        // Both traversals should add up to this same number.
        long expectedSum = (long) size * (size - 1) / 2;

        // ---- Traversal 1: Iterator ----
        long startTime = System.currentTimeMillis();

        long iteratorSum = 0;
        int iteratorCount = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iteratorSum += iterator.next();
            iteratorCount++;
        }

        long iteratorTime = System.currentTimeMillis() - startTime;

        // ---- Traversal 2: get(index) ----
        startTime = System.currentTimeMillis();

        long getSum = 0;
        int getCount = 0;
        for (int i = 0; i < list.size(); i++) {
            getSum += list.get(i);
            getCount++;
        }

        long getTime = System.currentTimeMillis() - startTime;

        // ---- Show the timing results ----
        System.out.println("Iterator traversal time:   " + iteratorTime + " ms");
        System.out.println("get(index) traversal time: " + getTime + " ms");

        // ---- Test code: make sure both traversals worked correctly ----
        checkResult("Iterator visited every element", iteratorCount == size);
        checkResult("get(index) visited every element", getCount == size);
        checkResult("Iterator sum is correct", iteratorSum == expectedSum);
        checkResult("get(index) sum is correct", getSum == expectedSum);
        checkResult("Both traversals read the same values", iteratorSum == getSum);

        System.out.println();
    }

    /**
     * Prints PASS or FAIL for a single test check.
     *
     * @param description what is being checked
     * @param passed      true if the check passed
     */
    public static void checkResult(String description, boolean passed) {
        if (passed) {
            System.out.println("PASS: " + description);
        } else {
            System.out.println("FAIL: " + description);
        }
    }
}
