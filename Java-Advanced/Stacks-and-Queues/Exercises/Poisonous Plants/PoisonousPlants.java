package com.company;

import java.util.*;

public class PoisonousPlants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= Integer.parseInt(scanner.nextLine());
        int[] plants= Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[]days= new int[n];

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(0);
        for (int i = 1; i < plants.length; i++) {
            int day=0;
            while (!stack.isEmpty()&&plants[stack.peek()]>=plants[i]){
                day=Math.max(day,days[stack.pop()]);
            }
            if (!stack.isEmpty()) {
                days[i]=day+1;
            }
            stack.push(i);
        }
        System.out.println(Arrays.stream(days).max().getAsInt());
    }
}
