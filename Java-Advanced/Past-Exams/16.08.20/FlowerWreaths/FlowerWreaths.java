import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> lilies = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .forEach(lilies::push);

        ArrayDeque<Integer> roses = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int wreaths = 0;
        int storedFlowers = 0;
        while (!lilies.isEmpty() && !roses.isEmpty()) {
            int nLilies = lilies.pop();
            int nRoses = roses.poll();
            int sum = nLilies + nRoses;
            while (sum > 15) {
                sum -= 2;
            }
            if (sum == 15) {
                wreaths++;
            } else {
                storedFlowers += sum;
            }
        }
        if (storedFlowers > 0) {
            wreaths += storedFlowers / 15;
        }
        if (wreaths >= 5) {
            System.out.println("You made it, you are going to the competition with " + wreaths + " wreaths!");
        } else {
            System.out.println("You didn't make it, you need " + (5 - wreaths) + " wreaths more!");
        }
    }
}
