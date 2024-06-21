package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
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
//        seedData();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter exercise number:");

        int exerciseNumber = Integer.parseInt(scanner.nextLine());

        switch (exerciseNumber) {
            case 1 -> bookTitleByAgeRestriction();
            case 2 -> goldenBooks();
            case 3 -> bookByPrice();
            case 4 -> notReleaseBook();
            case 5 -> bookReleasedBeforeDate();
            case 6 -> authorSearch();
            case 7 -> bookSearch();
            case 8 -> bookTitlesSearch();
            case 9 -> countBooks();
            case 10 -> totalBookCopies();
            case 11 -> reduceBook();
            default -> System.out.println("Invalid exercise number!");
        }
    }


    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }

    private void bookTitleByAgeRestriction() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter age restriction: ");
        this.bookService.findAllBooksByAgeRestriction(AgeRestriction.valueOf(scanner.nextLine().toUpperCase()));
    }

    private void goldenBooks() {
        this.bookService.findAllBooksByEditionAndCopies(EditionType.GOLD, 5000);
    }

    private void bookByPrice() {
        this.bookService.findAllBooksWithPriceNotBetween(5, 40);
    }

    private void notReleaseBook() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        this.bookService.findAllBooksNotReleasedIn(year);
    }

    private void bookReleasedBeforeDate() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date: ");
        String date = scanner.nextLine();
        this.bookService.findBooksReleasedBefore(date);
    }

    private void authorSearch() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name ending letter(s): ");
        String ending = scanner.nextLine();
        this.authorService.findByFirstNameEndingWith(ending);

    }

    private void bookSearch() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter letter(s): ");
        String input = scanner.nextLine();
        this.bookService.findAllTitlesContaining(input);

    }

    private void bookTitlesSearch() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter last name starting letter(s): ");
        String lastNameStarting = scanner.nextLine();
        this.bookService.findByAuthorLastNameStartsWith(lastNameStarting);

    }

    private void countBooks() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter length: ");
        int length = Integer.parseInt(scanner.nextLine());
        int counter = this.bookService.findAllBooksWithTitleLongerThan(length);
        System.out.println(counter);

    }

    private void totalBookCopies() {
        this.authorService.getTotalCopies()
                .forEach(a -> System.out.printf("%s %s - %d\n",
                        a.getFirstName(),
                        a.getLastName(),
                        a.getCopies()));
    }

    private void reduceBook() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        this.bookService.findBook(title);

    }
}
