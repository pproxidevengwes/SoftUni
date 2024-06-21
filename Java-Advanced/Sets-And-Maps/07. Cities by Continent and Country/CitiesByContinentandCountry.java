package com.company;

import java.util.*;

public class CitiesByContinentandCountry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,LinkedHashMap<String,ArrayList<String>>> cities=new LinkedHashMap<>();

        int numCities=Integer.parseInt(scanner.nextLine());
        for(int i=0;i<numCities;i++){
            String action = scanner.nextLine();
            String [] cityToken=action.split(" ");
            String continent=cityToken[0];
            String country=cityToken[1];
            String city=cityToken[2];
            if(!cities.containsKey(continent)){
                cities.put(continent,new LinkedHashMap<>());
            }
            if(!cities.get(continent).containsKey(country)){
                cities.get(continent).put(country,new ArrayList<>());
            }
            cities.get(continent).get(country).add(city);
        }

        cities.entrySet().stream().forEach(continent -> {
            System.out.println(continent.getKey()+":");
            continent.getValue().entrySet().stream().forEach(country -> {
                System.out.println("  "+country.getKey()+" -> "+country.getValue().toString().replaceAll("[\\[\\]]",""));
            });
        });

    }
}
