public class Point {
    private final int x;
    private final int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean graterOrEqual(Point a) {
        return a.x <= x && a.y <= y;
    }

    public boolean lessOrEqual(Point b) {
        return x <= b.x && y <= b.y;
    }
}
