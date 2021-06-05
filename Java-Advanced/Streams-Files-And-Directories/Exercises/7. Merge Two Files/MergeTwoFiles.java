package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MergeTwoFilesMain {
    public static void main(String[] args) throws IOException {
        String pathOne = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/inputOne.txt";
        String pathTwo = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/inputTwo.txt";
        String outputPath = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/outputMerge2.txt";

        PrintWriter writer = new PrintWriter(outputPath);
        Files.readAllLines(Path.of(pathOne))
                .forEach(writer::println);
        Files.readAllLines(Path.of(pathTwo))
                .forEach(writer::println);

        writer.close();

    }
}
