public class Rectangle {
    private final Point A;
    private final Point B;

    public Rectangle(Point a, Point b) {
        A = a;
        B = b;
    }

    public boolean contains(Point P) {
        return P.graterOrEqual(A) && P.lessOrEqual(B);
    }
}
