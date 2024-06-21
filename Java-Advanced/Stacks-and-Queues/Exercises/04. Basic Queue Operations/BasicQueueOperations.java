package com.company;

import java.util.*;

public class BasicQueueOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt(); //elemets to push
        int s= scanner.nextInt(); //elements to pop
        int x= scanner.nextInt(); //check

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            queue.offer(scanner.nextInt());
        }
        for (int i = 1; i <= s; i++) {
            queue.poll();
        }
        if (queue.contains(x)) {
            System.out.println("true");
        }else {
            if (!queue.isEmpty()) {

                System.out.println(Collections.min(queue));
            }else {
                System.out.println(0);
            }
        }

    }
}
