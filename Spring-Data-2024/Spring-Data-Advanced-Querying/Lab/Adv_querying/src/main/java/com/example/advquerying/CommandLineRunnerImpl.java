package com.example.advquerying;

import com.example.advquerying.service.IngredientService;
import com.example.advquerying.service.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public CommandLineRunnerImpl(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        ex1(reader);
//        ex2();
//        ex3();
//        ex4();
//        ex5(reader);
//        ex6();
//        ex7(reader);
//        ex8();
//        ex9();
//        ex10();
//        ex11();
    }

    private void ex11() {
        System.out.println(this.ingredientService.updateIngredientPricesForGivenNames());
    }

    private void ex10() {
        System.out.println(this.ingredientService.updateIngredientPrices());
    }


    private void ex9() {
        System.out.println(this.ingredientService.deleteIngredientByName("Nettle"));
    }

    private void ex8() {
        System.out.println(this.shampooService.getAllShampoosWithCountOfIngredientsBelowNumber());
    }

    private void ex7(BufferedReader reader) throws IOException {
        this.shampooService.getAllShampoosContainingIngredient(List.of(reader.readLine().split(" ")))
                .forEach(System.out::println);
    }

    private void ex6() {
        System.out.println(this.ingredientService.countOfIngredientsWithPriceLesserThan(BigDecimal.valueOf(8.5)));
    }

    private void ex5(BufferedReader reader) throws IOException {
        this.ingredientService.getAllIngredientsNamesOrderByPriceAsc(List.of(reader.readLine().split(" ")))
                .forEach(System.out::println);
    }

    private void ex4() {
        this.ingredientService.getAllIngredientsWithStartingName("M")
                .forEach(System.out::println);
    }

    private void ex3() {
        this.shampooService.getAllShampoosWithPriceGreaterThan(BigDecimal.valueOf(5))
                .forEach(System.out::println);
    }

    private void ex2() {
        this.shampooService.getAllShampooByGivenSizeOrLabel("medium", 10)
                .forEach(System.out::println);
    }

    private void ex1(BufferedReader reader) throws IOException {
        this.shampooService.getAllShampoosByGivenSize(reader.readLine())
                .forEach(System.out::println);
    }
}
