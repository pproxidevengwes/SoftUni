package pizza;

import java.util.*;

public class Main {
    public static void main(String[] args)   {
        Scanner scanner = new Scanner(System.in);
        String[] info = scanner.nextLine().split("\\s+");
        String pizzaName = info[1];
        int n = Integer.parseInt(info[2]);

        Pizza pizza = new Pizza(pizzaName, n);

        String[] doughInfo = scanner.nextLine().split("\\s+");
        pizza.setDough(new Dough(doughInfo[1], doughInfo[2], Double.parseDouble(doughInfo[3])));

        String line = scanner.nextLine();
        while (!line.equals("END")) {
            String[] toppingInfo = line.split("\\s+");
            pizza.addTopping(new Topping(toppingInfo[1], Double.parseDouble(toppingInfo[2])));

            line = scanner.nextLine();
        }
        System.out.println(pizza.getName()+" - "+pizza.getOverallCalories());
    }
}
