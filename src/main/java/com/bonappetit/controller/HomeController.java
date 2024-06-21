package com.bonappetit.controller;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final RecipeService recipeService;
    private final UserService userService;
    public final UserSession userSession;

    public HomeController(RecipeService recipeService, UserService userService, UserSession userSession) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.userSession = userSession;
    }


    @GetMapping("/")
    public String notLoggedIn() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String LoggedIn(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        Map<CategoryName, List<Recipe>> allRecepies = recipeService.findAllByCategory();
        List<RecipeInfoDTO> favourites = userService.findFavourites(userSession.id())
                .stream()
                .map(RecipeInfoDTO::new)
                .toList();

        List<RecipeInfoDTO> cocktails = allRecepies.get(CategoryName.COCKTAIL)
                .stream()
                .map(RecipeInfoDTO::new)
                .toList();
        List<RecipeInfoDTO> mainDishes = allRecepies.get(CategoryName.MAIN_DISH)
                .stream()
                .map(RecipeInfoDTO::new)
                .toList();
        List<RecipeInfoDTO> desserts = allRecepies.get(CategoryName.DESSERT)
                .stream()
                .map(RecipeInfoDTO::new)
                .toList();

        model.addAttribute("dessertsData", desserts);
        model.addAttribute("cocktailsData", cocktails);
        model.addAttribute("mainDishesData", mainDishes);
        model.addAttribute("favouritesData", favourites);

        return "home";
    }
}
