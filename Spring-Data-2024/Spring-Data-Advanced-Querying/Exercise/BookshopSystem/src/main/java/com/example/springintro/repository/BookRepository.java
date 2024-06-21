package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);


    List<Book> findTitlesByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findTitlesByEditionTypeAndCopiesLessThan(EditionType type, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal upper);

    List<Book> findAllByReleaseDateLessThanOrReleaseDateGreaterThan(LocalDate start, LocalDate end);

    List<Book> findAllByReleaseDateLessThan(LocalDate date);

    List<Book> findAllByTitleContaining(String needle);

    List<Book> findAllByAuthorLastNameStartingWith(String name);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :min")
    int countByTitleContains(int min);

    BookInfo findByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int updateAllByReleaseDateAfter(LocalDate date, int copies);

    @Transactional
    @Modifying
    int removeAllByCopiesLessThan(int copies);

    @Query(value = "CALL usp_get_books_by_author(:firstName, :lastName)", nativeQuery = true)
    int findAllBooksByAuthor(String firstName, String lastName);
}
