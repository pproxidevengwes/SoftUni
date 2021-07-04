package Lights;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] colors = scanner.nextLine().split("\\s+");
        int changeColor = Integer.parseInt(scanner.nextLine());

        List<Light> lightList = new ArrayList<>();

        for (String color : colors) {
            Light light = new Light(Color.valueOf(color));
            lightList.add(light);
        }

        for (int i = 0; i < changeColor; i++) {
            lightList.forEach(light -> {
                light.changeColor();
                System.out.print(light.getColor() + " ");
            });
            System.out.println();
        }
    }
}
