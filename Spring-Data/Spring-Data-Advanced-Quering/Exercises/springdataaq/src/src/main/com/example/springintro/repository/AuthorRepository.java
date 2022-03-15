package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNamesWithTotalCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author as a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findByFirstNameEndingWith(String letters);

    @Query("SELECT a.firstName as firstName, a.lastName as lastName, SUM(b.copies) as copies " +
            "FROM Author as a " +
            "JOIN a.books as b " +
            "GROUP BY b.author " +
            "ORDER BY copies DESC ")
    List<AuthorNamesWithTotalCopies> findAuthorCopies();
}
