import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String productCooking = "";
        int bread = 0;
        int cake = 0;
        int fruitPie = 0;
        int pastry = 0;
        //queue
        ArrayDeque<Integer> liquids = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        //stack
        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(ingredients::push);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int first = liquids.poll();
            int second = ingredients.peek();
            int sum = first + second;
            switch (sum) {
                case 25:
                    productCooking = "Bread";
                    bread++;
                    ingredients.pop();
                    break;
                case 50:
                    productCooking = "Cake";
                    cake++;
                    ingredients.pop();
                    break;
                case 75:
                    productCooking = "Pastry";
                    pastry++;
                    ingredients.pop();
                    break;
                case 100:
                    productCooking = "Fruit Pie";
                    fruitPie++;
                    ingredients.pop();
                    break;
                default:
                    ingredients.pop();
                    ingredients.addFirst(second + 3);
            }
        }
        if (bread != 0 && cake != 0 && pastry != 0 && fruitPie != 0) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to to cook everything.");
        }

        String liquidsLeft = liquids.isEmpty() ? "none" : liquids.stream()
                .map(Objects::toString).collect(Collectors.joining(", "));
        System.out.println("Liquids left: " + liquidsLeft);


        String ingredientsLeft = ingredients.isEmpty() ? "none" : ingredients.stream()
                .map(Objects::toString).collect(Collectors.joining(", "));
        System.out.println("Ingredients left: " + ingredientsLeft);

        System.out.println("Bread: " + bread);
        System.out.println("Cake: " + cake);
        System.out.println("Fruit Pie: " + fruitPie);
        System.out.println("Pastry: " + pastry);
    }
}
