package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class ProductShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,LinkedHashMap<String,Double>> shops=new TreeMap<>();
        String line="";
        while(!"Revision".equals(line = scanner.nextLine())){
            String [] command=line.split(", ");
            String name=command[0];
            String product=command[1];
            Double price=Double.parseDouble(command[2]);
            if(!shops.containsKey(name)){
                shops.put(name,new LinkedHashMap<>());
            }

            shops.get(name).put(product,price);
        }
        shops.entrySet().stream().forEach(shop -> {
            System.out.println(shop.getKey()+"->");
            shop.getValue().entrySet().stream().forEach(x -> System.out.printf("Product: %s, Price: %.1f%n",x.getKey(),x.getValue()));
        });

    }
}
