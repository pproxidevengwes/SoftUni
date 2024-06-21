package com.example.advquerying.service.impl;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.service.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<String> getAllShampoosByGivenSize(String size) {
        Size sizeEnum = Size.valueOf(size.toUpperCase());
        Set<Shampoo> allBySizeOrderBYId = this.shampooRepository.findAllBySizeOrderById(sizeEnum);
        return allBySizeOrderBYId
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.", shampoo.getLabel().getTitle(), shampoo.getSize().name(), shampoo.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampooByGivenSizeOrLabel(String size, long id) {
        Size sizeEnum = Size.valueOf(size.toUpperCase());
        Set<Shampoo> allBySizeOrderBYId = this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(sizeEnum, id);
        return allBySizeOrderBYId
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.", shampoo.getBrand(), shampoo.getSize().name(), shampoo.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampoosWithPriceGreaterThan(BigDecimal price) {
        Set<Shampoo> allBySizeOrderBYId = this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
        return allBySizeOrderBYId
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.", shampoo.getBrand(), shampoo.getSize().name(), shampoo.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampoosContainingIngredient(List<String> names) {
        Set<Shampoo> allByIngredientsNameIn = this.shampooRepository.findAllIngredientsNameIn(names);
        return allByIngredientsNameIn
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllShampoosWithCountOfIngredientsBelowNumber() {
        return this.shampooRepository.findAllWithIngredientsCountLesserThan(2)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toSet());
    }
}
