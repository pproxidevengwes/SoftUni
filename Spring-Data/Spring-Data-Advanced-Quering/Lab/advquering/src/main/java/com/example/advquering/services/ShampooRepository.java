package com.example.advquering.services;

import com.example.advquering.entities.Shampoo;
import com.example.advquering.entities.Size;
import com.example.advquering.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    @Autowired
    private ShampooRepository shampooRepository;


    @Override
    public List<Shampoo> selectBySize(Size size) {

        return this.shampooRepository.findShampoosBySizeOrderByIdAsc(size);
    }

    @Override
    public List<Shampoo> selectBySizeOrLabelId(Size size, Long id) {
        return this.shampooRepository.findShampoosBySizeOrLabelIdOrderByPriceAsc(size, id);
    }

    @Override
    public List<Shampoo> selectMoreExpensiveThan(BigDecimal price) {
        return this.shampooRepository.findShampoosByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int countPriceLowerThan(BigDecimal price) {
        return this.shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> selectByIngredientsCount(Set<String> names) {
        return this.shampooRepository.findByIngredientsName(names);
    }

    @Override
    public List<Shampoo> selectByIngredients(int count) {
        return this.shampooRepository.findByCountOfIngredients(count);
    }
}
