package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    void findAllBooksByAgeRestriction(AgeRestriction type);

    void findAllBooksByEditionAndCopies(EditionType type, int copies);

    void findAllBooksWithPriceNotBetween(float lower, float higher);

    void findAllBooksNotReleasedIn(int year);

    void findBooksReleasedBefore(String date);

    void findAllTitlesContaining(String letters);

    void findByAuthorLastNameStartsWith(String letters);

    int findAllBooksWithTitleLongerThan(int length);

    void findBook(String title);
}
