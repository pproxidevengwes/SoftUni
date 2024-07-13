package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) throws IOException {
        String pathWords = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/words.txt";
        String pathText = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/text.txt";
        String outputPath = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/outputCountWords.txt";

        PrintWriter writer = new PrintWriter(outputPath);
        LinkedHashMap<String, Integer> countWords = new LinkedHashMap<>();

        List<String> lines =  Files.readAllLines(Path.of(pathWords));

        for (String line: lines) {
            Arrays.stream(line.split("\\s+")).forEach(
                    word ->
                            countWords.put(word, 0)
            );
        }


        for (String line: Files.readAllLines(Path.of(pathText))) {
            Arrays.stream(line.split("\\s+")).forEach(
                    word ->
                    {
                        if(countWords.containsKey(word)) {
                            countWords.put(word, countWords.get(word) + 1);
                        }
                    }
            );
        }


        for ( Map.Entry<String, Integer> word: countWords.entrySet()) {
            writer.println(word.getKey() + " - " + word.getValue());
        }

        writer.close();



    }
}
