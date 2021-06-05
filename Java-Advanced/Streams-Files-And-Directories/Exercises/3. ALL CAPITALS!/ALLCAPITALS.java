package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ALLCAPITALS {
    public static int countRow = 1;
    public static void main(String[] args) throws IOException {
        String path ="C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/input.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/output.txt"));
        Files.readAllLines(Path.of(path))
                .forEach(str -> {
                    try {
                        writer.write(str.toUpperCase());
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        writer.close();
    }
}
