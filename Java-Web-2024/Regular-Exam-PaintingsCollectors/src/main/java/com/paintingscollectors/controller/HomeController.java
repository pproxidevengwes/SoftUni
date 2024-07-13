package com.paintingscollectors.controller;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.PaintingDisplayDTO;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    public final UserSession userSession;
    private final PaintingService paintingService;
    private final UserService userService;

    public HomeController(UserSession userSession, PaintingService paintingService, UserService userService) {
        this.userSession = userSession;
        this.paintingService = paintingService;
        this.userService = userService;
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
    public String loggedIn(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }


        Long userId = userSession.id();

        List<PaintingDisplayDTO> userPaintings = this.paintingService.getPaintingsForUser(userId);
        model.addAttribute("userPaintings", userPaintings);

        List<PaintingDisplayDTO> otherPaintings = this.paintingService.getPaintingsForOtherUsers(userId);
        model.addAttribute("otherPaintings", otherPaintings);

        Set<PaintingDisplayDTO> favoritePaintings =
                userService.getAllFavoritePaintings(userId);
        model.addAttribute("favoritePaintings", favoritePaintings);

        Set<PaintingDisplayDTO> mostRated =
                paintingService.getMostRated();
        model.addAttribute("mostRated", mostRated);


        return "home";
    }

    @GetMapping("/paintings/add-to-favorite/{id}")
    @Transactional
    public String addToFavorites(@PathVariable long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        paintingService.addToFavorites(userSession.id(), id);
        return "redirect:/home";
    }

    @GetMapping("/paintings/remove-from-favorites/{id}")
    @Transactional
    public String removeFromFavorites(@PathVariable long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        paintingService.removeFromFavorites(userSession.id(), id);
        return "redirect:/home";
    }

    @GetMapping("/paintings/delete-painting/{id}")
    @Transactional
    public String deletePainting(@PathVariable long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        paintingService.deletePainting(id);
        return "redirect:/home";
    }

    @GetMapping("/paintings/vote/{id}")
    @Transactional
    public String vote(@PathVariable long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        paintingService.vote(userSession.id(), id);
        return "redirect:/home";
    }
}

