package com.exercise2.mohit.sharma;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindRepeatedIntegers {
	// Student Name: Mohit Sharma
    // Student Number: 301297059

    public static List<Integer> findRepeatedIntegers(int[] B) {
        if (B == null || B.length < 6) {
            return new ArrayList<>(); // Handle invalid input
        }

        int n = B.length;
        int maxVal = n - 5;

        for (int x : B) {
            if (x < 1 || x > maxVal) {
                return new ArrayList<>(); // Handle invalid input (out of range)
            }
        }

        Arrays.sort(B); // Sort the array in-place. Crucial for efficiency.
        List<Integer> repeatedIntegers = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            if (B[i] == B[i + 1]) {
                if (!repeatedIntegers.contains(B[i])) { // Avoid duplicates
                    repeatedIntegers.add(B[i]);
                    count++;
                    if (count == 5) {
                        break; // Found all five, exit early
                    }
                }
            }
        }

        return repeatedIntegers;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 2, 3, 4, 5, 1, 2, 3, 4, 5},  // Basic case
                {1, 1, 2, 2, 3, 3, 4, 4, 5, 5},  // All repeated
                {1, 2, 3, 4, 5, 6, 1, 2, 3, 1},  // Some repeats, some unique
                {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2}, //More than 5 repeats
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5}, //n > 10
                {}, //Empty array
                {1,2,3,4,5}, //Too short
                {1,2,3,4,5,1,2,3,4, 10}, //Invalid value (10 outside of 1-5 range)
                {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5} //All 5 repeated more than twice.

        };

        for (int[] case_ : testCases) {
            List<Integer> result = findRepeatedIntegers(case_);
            System.out.println("Input: " + Arrays.toString(case_));
            System.out.println("Repeated integers: " + result);
            System.out.println("--------------------");
        }


        int[] B = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        List<Integer> repeated = findRepeatedIntegers(B);
        System.out.println("Repeated integers in B: " + repeated);
    }
}