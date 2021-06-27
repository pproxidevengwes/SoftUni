import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OSPlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //stack
        ArrayDeque<Integer> tasks = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .forEach(tasks::push);
        //queue
        ArrayDeque<Integer> threads = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        int taskToKill= Integer.parseInt(scanner.nextLine());

        int killerthread=0;
        while (!tasks.isEmpty()&&!threads.isEmpty()){
            int taskValue=tasks.peek();
            int threadValue=threads.peek();
            if (taskValue==taskToKill) {
                killerthread=threadValue;
                break;

            }
            if (threadValue>=taskValue) {
                tasks.pop();
                threads.poll();
            }else {
                threads.poll();
            }
        }
        System.out.printf("Thread with value %d killed task %d%n",killerthread,taskToKill);
        String threadsAsString = threads.isEmpty() ? "" : threads.stream()
                .map(Objects::toString).collect(Collectors.joining(" "));
        System.out.println(threadsAsString);

    }
}
