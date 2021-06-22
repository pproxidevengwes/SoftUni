import java.util.*;
import java.util.stream.Collectors;

public class Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, String> bombs = new HashMap<>();
        Map<String, Integer> bombsCount = new TreeMap<>();
      
        //queue
        ArrayDeque<Integer> bombEffects = Arrays.stream(scanner.nextLine().split(",\\s*"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        //stack
        ArrayDeque<Integer> bombCasings = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(",\\s*"))
                .map(Integer::parseInt)
                .forEach(bombCasings::push);

        bombs.put(40, "Datura Bombs");
        bombs.put(60, "Cherry Bombs");
        bombs.put(120, "Smoke Decoy Bombs");

        bombsCount.put("Datura Bombs", 0);
        bombsCount.put("Cherry Bombs", 0);
        bombsCount.put("Smoke Decoy Bombs", 0);

        boolean isBombPouchFilled = false;
      
        while (!bombEffects.isEmpty() && !bombCasings.isEmpty() && !isBombPouchFilled) {
            isBombPouchFilled = true;
            int effect = bombEffects.poll();
            int casing = bombCasings.pop();
            int sum = effect + casing;

            while (!bombs.containsKey(sum)) {
                casing -= 5;
                sum = effect + casing;
                if (sum < 40 && casing <= 0) {
                    break;
                }
            }

            String bomb = bombs.get(sum);
            if (bomb != null) {
                int count = bombsCount.get(bomb);
                bombsCount.put(bomb, count + 1);
            }

            for (Integer c : bombsCount.values()) {
                if (c < 3) {
                    isBombPouchFilled = false;
                    break;
                }
            }
        }

        if (isBombPouchFilled) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }

        String bombEffectsAsString = bombEffects.isEmpty() ? "empty" : bombEffects.stream()
                .map(Objects::toString).collect(Collectors.joining(", "));
        System.out.println("Bomb Effects: " + bombEffectsAsString);

        String bombCasingsAsString = bombCasings.isEmpty() ? "empty" : bombCasings.stream()
                .map(Objects::toString).collect(Collectors.joining(", "));
        System.out.println("Bomb Casings: " + bombCasingsAsString);

        bombsCount.forEach((k, v) -> System.out.println(k + ": " + v));


    }
}
