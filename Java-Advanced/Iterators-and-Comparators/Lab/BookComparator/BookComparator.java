import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        int result = a.getTitle().compareTo(b.getTitle());
        return result != 0 ? result : Integer.compare(a.getYear(), b.getYear());
    }
}
