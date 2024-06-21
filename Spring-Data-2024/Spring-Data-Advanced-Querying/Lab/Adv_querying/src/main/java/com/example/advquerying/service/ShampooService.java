package com.example.advquerying.service;

import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {

    List<String> getAllShampoosByGivenSize(String size);

    List<String> getAllShampooByGivenSizeOrLabel(String size, long id);

    List<String> getAllShampoosContainingIngredient(List<String> names);

    List<String> getAllShampoosWithPriceGreaterThan(BigDecimal price);

    Set<String> getAllShampoosWithCountOfIngredientsBelowNumber();
}
