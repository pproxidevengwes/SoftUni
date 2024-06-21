public class Main {
    public static void main(String[] args) {
      
        Book book1 = new Book("Animal Farm", 2003, "George Orwell");
        Book book2 = new Book("The Documents in the Case", 1930, "Dorothy Sayers", "Robert Eustace");

        if (book1.compareTo(book2) > 0) {
            System.out.printf("%s is before %s%n", book1, book2);
        } else if (book1.compareTo(book2) < 0) {
            System.out.printf("%s is before %s%n", book2, book1);
        } else {
            System.out.printf("Book are equal");
        }
    }
}
