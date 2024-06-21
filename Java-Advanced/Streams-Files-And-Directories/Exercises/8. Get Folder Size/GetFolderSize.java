package com.company;

import java.io.*;

public class GetFolderSize {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Admin\\Downloads\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources";
        File folder = new File(path);

        int folderSize = 0;
        for (File file : folder.listFiles()) {
            folderSize += file.length();
        }

        System.out.println("Folder size: " + folderSize);

    }
}
