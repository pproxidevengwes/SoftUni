import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MagicBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //queue
        ArrayDeque<Integer> first = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        //stack
        ArrayDeque<Integer> second = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(second::push);

        int calmedItems = 0;
        while (!first.isEmpty() && !second.isEmpty()) {
            int item1 = first.peek();
            int item2 = second.pop();
            int sum = item1 + item2;
            if (sum % 2 == 0) {
                calmedItems += sum;
                first.poll();
            } else {
                first.offer(item2);
            }
        }
        if (first.isEmpty()) {
            System.out.println("First magic box is empty.");
        }

        if (second.isEmpty()) {
            System.out.println("Second magic box is empty.");
        }

        if (calmedItems >= 90) {
            System.out.println("Wow, your prey was epic! Value: " + calmedItems);
        } else {
            System.out.println("Poor prey... Value: " + calmedItems);
        }
    }
}
