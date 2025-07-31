package com.exercise2.mohit.sharma;

import java.util.Scanner;

public class VowelCounter {
    // Method to check if a character is a vowel
    public static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    // Recursive method to count vowels in a string
    public static int countVowels(String str, int index) {
        if (index == str.length()) {
            return 0;
        }
        return (isVowel(str.charAt(index)) ? 1 : 0) + countVowels(str, index + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        scanner.close();
        
        int vowelCount = countVowels(input, 0);
        System.out.println("Number of vowels: " + vowelCount);
    }
}

