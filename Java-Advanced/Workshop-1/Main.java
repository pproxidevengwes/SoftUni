import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartArray smartArray = new SmartArray();

        smartArray.add(13);
        smartArray.add(73);
        smartArray.add(42);
        System.out.println(smartArray.remove(1));

        boolean contains = smartArray.contains(13);
        List<Integer> nums = new ArrayList<>();
        nums.add(13);
        nums.add(73);
        nums.add(42);
        contains = smartArray.contains(13);
        System.out.println(nums.remove(1));

    }
}
