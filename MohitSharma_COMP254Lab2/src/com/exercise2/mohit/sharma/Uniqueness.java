package com.exercise2.mohit.sharma;

import java.util.Arrays;

public class Uniqueness {

	  /** Returns true if there are no duplicate elements in the array. */
	  public static boolean unique1(int[] data) {
	    int n = data.length;
	    for (int j=0; j < n-1; j++)
	      for (int k=j+1; k < n; k++)
	        if (data[j] == data[k])
	          return false;                    // found duplicate pair
	    return true;                           // if we reach this, elements are unique
	  }

	  /** Returns true if there are no duplicate elements in the array. */
	  public static boolean unique2(int[] data) {
	    int n = data.length;
	    int[] temp = Arrays.copyOf(data, n);   // make copy of data
	    Arrays.sort(temp);                     // and sort the copy
	    for (int j=0; j < n-1; j++)
	      if (temp[j] == temp[j+1])            // check neighboring entries
	        return false;                      // found duplicate pair
	    return true;                           // if we reach this, elements are unique
	  }

  // Helper method to generate random data
  public static int[] generateData(int n) {
    int[] data = new int[n];
    for (int i = 0; i < n; i++) {
      data[i] = (int) (Math.random() * 10000); // Random values between 0 and 9999
    }
    return data;
  }

  // Helper method to measure execution time
  public static long measureExecutionTime(Runnable algorithm, int[] data) {
    long startTime = System.nanoTime();
    algorithm.run();
    long endTime = System.nanoTime();
    return endTime - startTime;
  }

  public static void main(String[] args) {
    // Set the maximum allowed time (1 minute = 60 seconds = 60 * 10^9 nanoseconds)
    final long MAX_TIME = 60_000;

    // Binary search for maximum n for unique1
    int left = 0, right = 100000; // Start with a reasonable upper bound for n
    int maxN1 = 0;
    while (left <= right) {
      int mid = (left + right) / 2;
      int[] data = generateData(mid);
      long timeTaken = measureExecutionTime(() -> unique1(data), data);
      if (timeTaken <= MAX_TIME) {
        maxN1 = mid;
        left = mid + 1; // Try larger n
      } else {
        right = mid - 1; // Try smaller n
      }
    }

    // Binary search for maximum n for unique2
    left = 0;
    right = 100000; // Start with a reasonable upper bound for n
    int maxN2 = 0;
    while (left <= right) {
      int mid = (left + right) / 2;
      int[] data = generateData(mid);
      long timeTaken = measureExecutionTime(() -> unique2(data), data);
      if (timeTaken <= MAX_TIME) {
        maxN2 = mid;
        left = mid + 5; // Try larger n
      } else {
        right = mid - 5; // Try smaller n
      }
    }

    // Print the results
    System.out.println("Finding max N for unique1...");
    System.out.println("Maximum n for unique1: " + maxN1);
    System.out.println("Finding max N for unique2...");
    System.out.println("Maximum n for unique2: " + maxN2);
  }
}
