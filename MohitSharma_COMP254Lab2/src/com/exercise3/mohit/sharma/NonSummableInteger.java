package com.exercise3.mohit.sharma;

import java.util.HashSet;

public class NonSummableInteger {

    public static int findNonSummableInteger(int[] A) {
        int maxVal = Integer.MIN_VALUE;
        HashSet<Integer> set = new HashSet<>();

        // Find the maximum element and store elements in a HashSet
        for (int num : A) {
            maxVal = Math.max(maxVal, num);
            set.add(num);
        }

        int limit = 2 * maxVal; // The highest sum of any two elements
        boolean[] sumPossible = new boolean[limit + 1];

        // Mark all possible sums of two elements in the array
        for (int num1 : A) {
            for (int num2 : A) {
                if (num1 != num2) { // Ensure different elements are used
                    sumPossible[num1 + num2] = true;
                }
            }
        }

        // Find the smallest integer that is not a sum of any two elements
        for (int i = 1; i <= limit; i++) {
            if (!sumPossible[i]) {
                return i;
            }
        }
        return -1; // Should not reach here if array contains at least two distinct elements
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 10}; // Example array
        System.out.println("Smallest integer that cannot be formed as the sum of two elements: " + findNonSummableInteger(A));
    }
}
