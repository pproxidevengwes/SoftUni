public class Circle extends Shape {
    private final Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    private final Double getRadius() {
        return radius;
    }

    @Override
    Double calculatePerimeter() {
        if (getPerimeter() == null) {
            setPerimeter(2 * Math.PI * getRadius());
        }
        return getPerimeter();
    }

    @Override
    Double calculateArea() {
        if (getArea() == null) {
            setArea(Math.PI * Math.pow(this.radius, 2));
        }
        return getArea();
    }
}
