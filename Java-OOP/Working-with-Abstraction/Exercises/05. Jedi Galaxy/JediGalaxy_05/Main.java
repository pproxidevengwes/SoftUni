package JediGalaxy_05;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Galaxy galaxy = new Galaxy(dimensions[0], dimensions[1]);

        StarsManipulator starManipulator = new StarsManipulator(galaxy);

        long sum = 0;
        String line = scanner.nextLine();
        while (!line.equalsIgnoreCase("Let the Force be with you")) {
            int[] playerPosition = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] enemyPositions = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            starManipulator.destroyStars(enemyPositions);

            sum += starManipulator.sumOfStars(playerPosition);

            line = scanner.nextLine();
        }
        System.out.println(sum);
    }
}
