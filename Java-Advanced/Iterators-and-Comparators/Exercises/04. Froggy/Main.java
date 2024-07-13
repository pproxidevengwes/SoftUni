import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> arr = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());
      
        Lake lake = new Lake(arr);

        Iterator<Integer> frog = lake.iterator();
        List<String> result = new ArrayList<>();
        while (frog.hasNext()) {
            result.add(frog.next() + "");
        }
        System.out.println(String.join(", ", result));

    }
}
