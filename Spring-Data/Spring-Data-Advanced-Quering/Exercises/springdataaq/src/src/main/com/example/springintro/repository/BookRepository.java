package com.example.springintro.repository;

import com.example.springintro.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findByAgeRestriction(AgeRestriction type);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType type, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal higher);

    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findByReleaseDateBefore(LocalDate date);

    List<Book> findByTitleContaining(String letters);

    List<Book> findByAuthorLastNameStartingWith(String letters);

    @Query("SELECT count(b) as count FROM Book as b " +
            "WHERE length(b.title) > :length")
    int findByTitleGreaterThanGivenLength(int length);


    @Query("SELECT b.title as title, b.editionType as editionType, b.ageRestriction as ageRestriction," +
            "b.price as price FROM Book as b " +
            "WHERE b.title = :title")
    List<BookSummary> findInformationAboutGivenBook(String title);
}
