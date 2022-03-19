package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        seedData();

//        01.
//        System.out.print("Enter age restriction: ");
//        this.bookService.findAllBooksByAgeRestriction(AgeRestriction.valueOf(scanner.nextLine().toUpperCase()));

//        02.
//        this.bookService.findAllBooksByEditionAndCopies(EditionType.GOLD,5000);

//        03.
//        this.bookService.findAllBooksWithPriceNotBetween(5,40);

//        04.
//        System.out.print("Enter year: ");
//        this.bookService.findAllBooksNotReleasedIn(scanner.nextInt());

//        05.
//        System.out.print("Enter date: ");
//        this.bookService.findBooksReleasedBefore(scanner.nextLine());

//        06.
//        System.out.print("Enter first name ending letter(s): ");
//        this.authorService.findByFirstNameEndingWith(scanner.nextLine());

//        07.
//        System.out.print("Enter letter(s): ");
//        this.bookService.findAllTitlesContaining(scanner.nextLine());

//        08.
//        System.out.print("Enter last name starting letter(s): ");
//        this.bookService.findByAuthorLastNameStartsWith(scanner.nextLine());

//        09.
//        System.out.print("Enter length: ");
//        int length = Integer.parseInt(scanner.nextLine());
//        System.out.println(this.bookService.findAllBooksWithTitleLongerThan(length));

//        10.
//        this.authorService.getTotalCopies()
//                .forEach(a -> System.out.printf("%s %s - %d\n",
//                        a.getFirstName(),
//                        a.getLastName(),
//                        a.getCopies()));

//        11.
//        System.out.print("Enter book title: ");
//        this.bookService.findBook(scanner.nextLine().trim);

    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
