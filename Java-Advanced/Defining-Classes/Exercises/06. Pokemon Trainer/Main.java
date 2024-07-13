import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Trainer> trainerMap = new LinkedHashMap<>();

        String line = scanner.nextLine();
        while (!line.equals("Tournament")) {
            String trainerName = line.split("\\s+")[0];
            trainerMap.putIfAbsent(trainerName, new Trainer(trainerName));
            String pokemonName = line.split("\\s+")[1];
            String element = line.split("\\s+")[2];
            int health = Integer.parseInt(line.split("\\s+")[3]);
            Pokemon pokemon = new Pokemon(pokemonName, element, health);
            trainerMap.get(trainerName).addPokemon(pokemon);
            line = scanner.nextLine();
        }
        String element = scanner.nextLine();
        while (!element.equals("End")) {
            switch (element) {
                case "Fire":
                    reducePokemonsHealthAndIncreaseBadges("Fire", trainerMap);
                    break;
                case "Water":
                    reducePokemonsHealthAndIncreaseBadges("Water", trainerMap);
                    break;
                case "Electricity":
                    reducePokemonsHealthAndIncreaseBadges("Electricity", trainerMap);
                    break;
            }
            element = scanner.nextLine();
        }
        trainerMap.values().stream().sorted((a, b) ->
                Integer.compare(b.getBadges(), a.getBadges()))
                .forEach(System.out::println);
    }

    private static void reducePokemonsHealthAndIncreaseBadges(String element, Map<String, Trainer> trainerMap) {
        for (var entry : trainerMap.entrySet()) {
            if (entry.getValue().checkForPokemons(element)) {
                entry.getValue().increaseBadges();
            } else {
                entry.getValue().reducePokemonHealth();
            }
        }
    }
}
