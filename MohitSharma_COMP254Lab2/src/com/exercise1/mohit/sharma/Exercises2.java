

/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Solution for the exercises on asymptotics.
 * 
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
package com.exercise1.mohit.sharma;
public class Exercises2 {

    // Computes the sum of first 'n' natural numbers - O(n)
    public static int computeSum(int n) {
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += i;
        }
        return total;
    }

    // Computes the product of numbers up to '2n' - O(n)
    public static int computeProduct(int n) {
        int product = 1;
        for (int i = 1; i <= 2 * n; i++) {
            product *= i;
        }
        return product;
    }

    // Computes the factorial-like product up to n^2 - O(n²)
    public static int quadraticProduct(int n) {
        int result = 1;
        for (int i = 1; i <= n * n; i++) {
            result *= i;
        }
        return result;
    }

    // Computes a sum using nested loops, growing quadratically - O(n²)
    public static int nestedLoopSum(int n) {
        int sum = 0;
        for (int i = 1; i <= 2 * n; i++) {
            for (int j = 1; j <= i; j++) {
                sum += i;
            }
        }
        return sum;
    }

    // Computes a sum using nested loops, growing quartically - O(n⁴)
    public static int quarticGrowthSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n * n; i++) {
            for (int j = 1; j <= i; j++) {
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int testValue = 3; // Change this for different test cases

        System.out.println("computeSum(" + testValue + ") = " + computeSum(testValue)); // O(n)
        System.out.println("computeProduct(" + testValue + ") = " + computeProduct(testValue)); // O(n)
        System.out.println("quadraticProduct(" + testValue + ") = " + quadraticProduct(testValue)); // O(n²)
        System.out.println("nestedLoopSum(" + testValue + ") = " + nestedLoopSum(testValue)); // O(n²)
        System.out.println("quarticGrowthSum(" + testValue + ") = " + quarticGrowthSum(testValue)); // O(n⁴)
    }
}