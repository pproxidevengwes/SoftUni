package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SumLines {
    public static void main(String[] args) throws IOException {
        String path="C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources/inputLineNumbers.txt";
        Files.readAllLines(Path.of(path)).stream()
                .map(String::toCharArray)
                .forEach(charArray->
                        {
                            int sum=0;
                            for (char symbol : charArray) {
                                sum+=symbol;
                            }
                            System.out.println(sum);
                        });

    }
}
