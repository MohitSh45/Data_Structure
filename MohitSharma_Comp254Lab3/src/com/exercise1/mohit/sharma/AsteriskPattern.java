package com.exercise1.mohit.sharma;

import java.util.Scanner;

public class AsteriskPattern {
    public static void printPattern(int n, int current) {
        // Base case
        if (current > n) {
            return;
        }
        
        // Print asterisks
        for (int i = 0; i < current; i++) {
            System.out.print("*");
        }
        System.out.println();
        
        // Recursive call to increase the number of asterisks
        printPattern(n, current + 1);
        
        // Print decreasing pattern after reaching the max
        if (current < n) {
            for (int i = 0; i < current; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the maximum number of asterisks: ");
        int n = scanner.nextInt();
        scanner.close();
        
        printPattern(n, 1);
    }
}

