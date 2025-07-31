package com.exercise1.mohit.sharma;

public class ReverseString {
    
    // Student Name: Mohit Sharma
    // Student Number: 301297059
    public static void main(String[] args) {
        String input = "java";
        String reversed = reverseString(input);
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + reversed);
    }
    
    public static String reverseString(String s) {
        if (s.isEmpty()) {
            return s;
        }
        return s.charAt(s.length() - 1) + reverseString(s.substring(0, s.length() - 1));
    }
}
