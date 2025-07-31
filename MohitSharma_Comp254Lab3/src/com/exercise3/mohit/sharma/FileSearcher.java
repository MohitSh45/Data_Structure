package com.exercise3.mohit.sharma;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSearcher {
    // Recursive method to find files with the given name
    public static List<String> findFiles(String path, String filename) {
        List<String> results = new ArrayList<>();
        File directory = new File(path);
        
        if (directory.exists() && directory.isDirectory()) {
            search(directory, filename, results);
        }
        
        return results;
    }
    
    private static void search(File directory, String filename, List<String> results) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    search(file, filename, results);
                } else if (file.getName().equals(filename)) {
                    results.add(file.getAbsolutePath());
                }
            }
        }
    }
    
    public static void readFileContent(String filePath) {
        File file = new File(filePath);
        try (Scanner fileScanner = new Scanner(file)) {
            System.out.println("\nContents of " + filePath + ":");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + filePath);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter directory path: ");
        String path = scanner.nextLine();
        System.out.print("Enter filename to search: ");
        String filename = scanner.nextLine();
        scanner.close();
        
        List<String> foundFiles = findFiles(path, filename);
        if (foundFiles.isEmpty()) {
            System.out.println("No files found with the name " + filename);
        } else {
            System.out.println("Found files:");
            for (String filePath : foundFiles) {
                System.out.println(filePath);
                readFileContent(filePath);
            }
        }
    }
}
