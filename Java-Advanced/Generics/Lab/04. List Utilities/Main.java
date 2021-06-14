import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> ints = List.of(13, 42, 73, 69, -66);
        System.out.println(ListUtils.getMax(ints));
        System.out.println(ListUtils.getMin(ints));
    }
}
