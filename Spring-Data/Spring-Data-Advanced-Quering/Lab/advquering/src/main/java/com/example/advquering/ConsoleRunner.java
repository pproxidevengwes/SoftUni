package com.example.advquering;

import com.example.advquering.entities.Size;
import com.example.advquering.services.IngredientService;
import com.example.advquering.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

//1.
//        this.shampooService.selectBySize(Size.MEDIUM)
//                .forEach(System.out::println);

//2.
//        this.shampooService.selectBySizeOrLabelId(Size.MEDIUM, 10)
//                .forEach(System.out::println);

//3.
//        this.shampooService.selectMoreExpensiveThan(BigDecimal.valueOf(5))
//                .forEach(s -> System.out.printf("%s %s %.2f\n",
//                        s.getBrand(),
//                        s.getSize(),
//                        s.getPrice()));

//4.
//        this.ingredientService.selectNameStartsWith("M")
//                .forEach(e -> System.out.printf("%s%n", e.getName()));

//5.
//        this.ingredientService.selectInNames(List.of("Lavender", "Herbs", "Apple"))
//               .forEach(i -> System.out.println(i.getName()));

//6.
//        System.out.println(this.shampooService.countPriceLowerThan(BigDecimal.valueOf(8.50)));

//7.
//        this.shampooService.selectByIngredientsCount (Set.of("Berry","Mineral-Collagen"))
//                .forEach(s -> System.out.printf("%s\n",s.getBrand()));

//8.
//        this.shampooService.selectByIngredients(2)
//                .forEach(s -> System.out.printf("%s\n",s.getBrand()));

//9.
//        this.ingredientService.deleteByName("Nettle");

//11.
//        this.ingredientService.increasePriceByPercentageAndName(Set.of("Apple","Nettle","Macadamia Oil"));

    }
}
