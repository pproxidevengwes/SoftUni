import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jar<Integer> integerJar = new Jar<>();
        
        integerJar.add(13);
        integerJar.add(43);
        System.out.println(integerJar.remove());
        System.out.println(integerJar.remove());
    }
}
