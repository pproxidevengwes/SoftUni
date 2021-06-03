package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class AverageStudentsGrades {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TreeMap<String, List<Double>> students = new TreeMap<>();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            Double grade = Double.parseDouble(input[1]);

            List<Double> studentGrades = new LinkedList<>();

            if (students.containsKey(input[0])) {
                studentGrades = students.get(input[0]);
            }
            studentGrades.add(grade);
            students.put(input[0], studentGrades);
        }

        students.forEach((key, value) -> {
            StringBuilder sb = new StringBuilder();
            value.forEach(c -> sb.append(String.format("%.2f ", c)));
            double sum = 0;
            for (Double X : value) {
                sum += X;
            }
            sum /= value.size();
            System.out.printf("%s -> %s(avg: %.2f)%n", key, sb, sum);
        });
    }

}
