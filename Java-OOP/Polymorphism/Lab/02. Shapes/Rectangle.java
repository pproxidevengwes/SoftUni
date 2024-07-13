public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    @Override
    Double calculateArea() {
        if (getArea() == null) {
            setArea(getHeight() * getWidth());
        }
        return this.getArea();
    }

    @Override
    Double calculatePerimeter() {
        if (getArea() == null) {
            setArea(2 * (getHeight() + getWidth()));
        }
        return getArea();
    }
}
