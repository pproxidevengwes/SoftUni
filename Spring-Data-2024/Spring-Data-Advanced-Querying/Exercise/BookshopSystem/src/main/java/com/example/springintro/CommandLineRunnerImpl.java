package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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

        // 1. Books Titles by Age Restriction
//        printBooksByAgeRestriction();
        // 2. Golden Books
//        printGoldenBooksWithLessThan5000Copies();
        // 3. Books by Price
//        printBooksWithPriceOutOfRange();
        // 4. Not Released Books
//        printBooksNotIssuedAt();
        //5. Books Released Before Dat
//        printBookInfoForBooksReleasedBefore();
        // 6. Authors Search
//        printAuthorsEndingIn();
        // 7. Books Search
//        printBookTitlesContaining();
        // 8. Book Titles Search
//        printAllBooksByLastNameStarting();
        // 9. Count Books
//        printStatsForTitlesLength();
        // 10. Total Book Copies
//        printTotalBookCopiesForAuthor();
        // 11. Reduced Book
//        printBookProjection();
        // 12. * Increase Book Copies
//        updateBookCopies();
        // 13. * Remove BooksteEN
//        deleteBooksWithCopies();
        // 14. * Stored Procedure
//        printBooksByAuthor();
    }

    private void printBooksByAuthor() {
        Scanner scanner = new Scanner(System.in);
        String[] name = scanner.nextLine().split("\\s+");
        int count = bookService.getAllBooksCountByAuthorNames(name[0], name[1]);
        System.out.printf("%s %s has written %d books%n", name[0], name[1], count);
    }

    private void deleteBooksWithCopies() {
        int removedBooksCount = bookService.removeAllByCopiesLessThan(600);
        System.out.println(removedBooksCount);
    }

    private void updateBookCopies() {
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine().replaceAll("\\s+", "-");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        LocalDate parsed = LocalDate.parse(date, dateTimeFormatter);
        int copies = Integer.parseInt(scanner.nextLine());

        int updatedCount = bookService.updateAllByReleaseDateAfter(parsed, copies);
        int totalAddedBooks = updatedCount * copies;
        System.out.println(totalAddedBooks);
    }

    private void printBookProjection() {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        BookInfo info = bookService.findInfoByTitle(title);
        System.out.println(info);
    }

    private void printTotalBookCopiesForAuthor() {
        Scanner scanner = new Scanner(System.in);
        String[] authorName = scanner.nextLine().split("\\s+");

        int count = authorService.findTotalCopiesCountFor(authorName[0], authorName[1]);
        System.out.printf("%s %s %d%n", authorName[0], authorName[1], count);
    }

    private void printStatsForTitlesLength() {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());

        int count = bookService.findTitleCountLongerThan(length);
        System.out.printf("There are %d books with longer titles than %d symbols%n", count, length);
    }

    private void printAllBooksByLastNameStarting() {
        Scanner scanner = new Scanner(System.in);
        String lastNameStart = scanner.nextLine();

        bookService.findTitlesForAuthorNameStartingWIth(lastNameStart)
                .forEach(System.out::println);
    }

    private void printBookTitlesContaining() {
        Scanner scanner = new Scanner(System.in);
        String needle = scanner.nextLine();

        List<String> titles = bookService.findTitlesContaining(needle);
        titles.forEach(System.out::println);
    }

    private void printAuthorsEndingIn() {
        Scanner scanner = new Scanner(System.in);
        String ending = scanner.nextLine();

        List<String> names = authorService.findAllNamesEndingIn(ending);
        names.forEach(System.out::println);
    }

    private void printBookInfoForBooksReleasedBefore() {
        Scanner scanner = new Scanner(System.in);
        String beforeDate = scanner.nextLine();

        LocalDate parseDate = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        List<Book> books = bookService.findAllReleasedBefore(parseDate);
        books.forEach(book -> System.out.printf("%s %s $%.2f%n", book.getTitle(), book.getEditionType(), book.getPrice()));
    }

    private void printBooksNotIssuedAt() {
        List<String> titles = bookService.findTitlesOfBooksNotPublishedIn(2000);
        titles.forEach(System.out::println);
    }

    private void printBooksWithPriceOutOfRange() {
        List<Book> books = bookService.findAllBooksWithPriceOutOfRange(5, 40);
        books.forEach(book -> System.out.printf("%s $%.2f%n", book.getTitle(), book.getPrice()));
    }

    private void printGoldenBooksWithLessThan5000Copies() {
        List<String> titles = bookService.findTitlesByEditionAndCopies(EditionType.GOLD, 5000);
        titles.forEach(System.out::println);

    }

    private void printBooksByAgeRestriction() {
        Scanner scanner = new Scanner(System.in);
        String restriction = scanner.nextLine();

        try {
            AgeRestriction ageRestriction = AgeRestriction.valueOf(restriction.toUpperCase());

            List<String> titles = bookService.findTitlesByAgeRestriction(ageRestriction);
            titles.stream().forEach(System.out::println);
        } catch (IllegalArgumentException ex) {
            System.out.println("Wrong age category");
            return;
        }
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
