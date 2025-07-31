package com.exercise1.mohit.sharma;

import java.util.Random;
import java.util.Scanner;

public class HashMapEfficiencyTest {
    public static void main(String[] args) {
        int initialCapacity = 10000;  // Default capacity for hash maps
        Scanner userInput = new Scanner(System.in);

        while (true) {
            System.out.print("Specify load factor: ");
            double userLoadFactor = userInput.nextDouble();

            if (userLoadFactor > 1) {
                System.out.println("Terminating execution...");
                break;
            }

            // Evaluate performance on ChainHashMap
            System.out.println("--- Evaluating ChainHashMap Performance ---");
            evaluatePerformance(new ChainHashMap<>(initialCapacity, 109345121, userLoadFactor), userLoadFactor);

            // Evaluate performance on ProbeHashMap
            System.out.println("--- Evaluating ProbeHashMap Performance ---");
            evaluatePerformance(new ProbeHashMap<>(initialCapacity, 109345121, userLoadFactor), userLoadFactor);
        }

        userInput.close();
    }

    // Method to assess hash map efficiency
    public static void evaluatePerformance(AbstractHashMap<Integer, String> hashMap, double loadFactor) {
        long startInsertionTime = System.nanoTime();
        Random randomGenerator = new Random();

        // Insert 10,000 key-value pairs with random keys
        for (int i = 0; i < 10000; i++) {
            hashMap.put(randomGenerator.nextInt(10000), "Data" + i);
        }
        long endInsertionTime = System.nanoTime();
        System.out.println("Insertion Time (Load Factor " + loadFactor + "): " + (endInsertionTime - startInsertionTime) + " ns");

        // Measure retrieval performance
        long startRetrievalTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            hashMap.get(randomGenerator.nextInt(10000));
        }
        long endRetrievalTime = System.nanoTime();
        System.out.println("Retrieval Time (Load Factor " + loadFactor + "): " + (endRetrievalTime - startRetrievalTime) + " ns\n");
    }
}
