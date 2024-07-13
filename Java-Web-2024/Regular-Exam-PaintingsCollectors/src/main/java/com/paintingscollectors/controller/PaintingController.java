package com.paintingscollectors.controller;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/painting")
public class PaintingController {
    private final UserSession userSession;
    private final PaintingService paintingService;

    public PaintingController(UserSession userSession, PaintingService paintingService) {
        this.userSession = userSession;
        this.paintingService = paintingService;
    }

    @ModelAttribute("paintingData")
    public AddPaintingDTO paintingDTO() {
        return new AddPaintingDTO();
    }

    @GetMapping("/add")
    public String addPainting() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        return "add-painting";
    }

    @PostMapping("/add")
    @Transactional
    public String doAddPainting(@Valid AddPaintingDTO paintingDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("paintingData", paintingDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.paintingData", bindingResult);

            return "redirect:/painting/add";
        }
        boolean successfullyAdded = this.paintingService.addPainting(paintingDTO, userSession.id());

        return "redirect:/home";
    }

}
