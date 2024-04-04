package org.bookshopsystem;

import org.bookshopsystem.service.AuthorService;
import org.bookshopsystem.service.BookService;
import org.bookshopsystem.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public CommandLineRunnerImpl(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
//        getAllBooksAfter2000Year();
//        getAllBooksBefore1990Year();
//        getAllAuthorsByBooksDesc();
//        printBooksByGeorgePowell();
        this.bookService.createBook();
    }

    private void printBooksByGeorgePowell() {
        this.bookService.findAllBooksByGeorgePowellOrdered()
                .forEach(System.out::println);
    }

    private void getAllAuthorsByBooksDesc() {
        this.authorService.getAllAuthorsDescBooks()
                .forEach(System.out::println);
    }

    private void getAllBooksBefore1990Year() {
        this.authorService.getAllAuthorsFirstAndLastNameForBooksBeforeYear1990()
                .forEach(System.out::println);
    }

    private void getAllBooksAfter2000Year() {
        this.bookService.findAllBooksAfterYear2000().forEach(System.out::println);
    }

    private void seedData() throws IOException {
        this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();
    }
}
