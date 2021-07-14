public abstract class Shape {
    private Double perimeter;
    private Double area;

    public Double getPerimeter() {
        return this.perimeter;
    }

    protected void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getArea() {
        return this.area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    abstract Double calculatePerimeter();

    abstract Double calculateArea();
}
