package org.bookshopsystem.data.repositories;

import org.bookshopsystem.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Set<Book> findBookByReleaseDateAfter(LocalDate date);

    Set<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(String firstName, String lastName);

}
