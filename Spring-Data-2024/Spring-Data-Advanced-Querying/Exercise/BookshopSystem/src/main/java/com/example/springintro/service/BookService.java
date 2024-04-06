package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findTitlesByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findTitlesByEditionAndCopies(EditionType type, int copies);


    List<Book> findAllBooksWithPriceOutOfRange(int lowerBound, int upperBound);

    List<String> findTitlesOfBooksNotPublishedIn(int year);

    List<Book> findAllReleasedBefore(LocalDate date);

    List<String> findTitlesContaining(String needle);

    List<String> findTitlesForAuthorNameStartingWIth(String lastNameStart);

    int findTitleCountLongerThan(int length);

    BookInfo findInfoByTitle(String title);

    int updateAllByReleaseDateAfter(LocalDate date, int copies);

    int removeAllByCopiesLessThan(int copies);

    int getAllBooksCountByAuthorNames(String firstName, String lastName);
}

