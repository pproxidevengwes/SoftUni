package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class LineNumbers {
    public static int countRow = 1;
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/inputLineNumbers.txt";
        String outputPath = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/outputLine.txt";

        PrintWriter writer = new PrintWriter(outputPath);

        Files.readAllLines(Path.of(path))
                .forEach(line ->
                        writer.println(countRow++ + ". " + line));

        writer.close();

    }
}
