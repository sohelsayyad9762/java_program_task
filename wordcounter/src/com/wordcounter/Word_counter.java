package com.wordcounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Word_counter {
    public static void main(String[] args) {
        try {
            // Step 1: Prompt the user for input (text or file)
            String userInput = getUserInput();

            // Step 2: Read the input text or file and store it in a string
            String text = readInput(userInput);

            if (text.isEmpty()) {
                System.out.println("Input is empty. Nothing to count.");
                return;
            }

            // Step 3: Split the string into an array of words using space or punctuation as delimiters
            String[] words = text.split("[\\s\\p{Punct}]+");

            // Step 4: Initialize a counter variable to keep track of the number of words
            int wordCount = 0;

            // Step 7: Define and load common stop words into a set
            Set<String> stopWords = loadStopWords();

            // Step 8: Initialize a map to store word frequencies
            Map<String, Integer> wordFrequencies = new HashMap<>();

            // Step 5: Iterate through the array of words
            for (String word : words) {
                // Step 7: Ignore common stop words
                if (!stopWords.contains(word.toLowerCase())) {
                    // Step 5: Increment the counter for each word encountered
                    wordCount++;

                    // Step 8: Update word frequencies
                    wordFrequencies.put(word.toLowerCase(), wordFrequencies.getOrDefault(word.toLowerCase(), 0) + 1);
                }
            }

            // Step 6: Display the total count of words to the user
            System.out.println("Total words: " + wordCount);

            // Step 9: Display unique words and their frequencies
            System.out.println("Unique words and their frequencies:");
            for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the input: " + e.getMessage());
        }
    }

    private static String getUserInput() {
        // You can implement logic to prompt the user for input (text or file) here.
        // For simplicity, I'll return a sample text for demonstration purposes.
        return "This is a sample text. You can replace it with your own input or file path.";
    }

    private static String readInput(String userInput) throws IOException {
        if (userInput.toLowerCase().endsWith(".txt")) {
            // User provided a file path
            return readFile(userInput);
        } else {
            // User provided plain text
            return userInput;
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static Set<String> loadStopWords() {
        // Define and load common stop words into a set
        Set<String> stopWords = new HashSet<>();
        stopWords.add("the");
        stopWords.add("and");
        stopWords.add("in");
        // Add more stop words as needed
        return stopWords;
    }
}
