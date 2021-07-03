import input.Reader;

public class Main {
    public static void main(String[] args) {
        int[] coordinates = Reader.readIntArray("\\s+");

        Point A = new Point(coordinates[0], coordinates[1]);
        Point B = new Point(coordinates[2], coordinates[3]);

        Rectangle rect = new Rectangle(A, B);

        int n = Reader.readArray("\\s+")[0];
        while (n-- > 0) {
            int[] singlePoint = Reader.readIntArray("\\s+");
            Point p = new Point(singlePoint[0], singlePoint[1]);

            boolean isWithin = rect.contains(p);
            printLine(isWithin);
        }

    }
}
