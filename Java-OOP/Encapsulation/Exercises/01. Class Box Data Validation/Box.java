public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);

    }

    public void setLength(double length) {
        if (isPositiveOrBiggerThanZero(length)) {
            this.length = length;
        }
    }

    public void setWidth(double width) {
        if (isPositiveOrBiggerThanZero(width)) {
            this.width = width;
        }
    }

    public void setHeight(double height) {
        if (isPositiveOrBiggerThanZero(height)) {
            this.height = height;
        }
    }

    private boolean isPositiveOrBiggerThanZero(double n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number must be bigger than zero");
        }
        return n > 0;
    }

    public double calculateSurfaceArea() {
        return 2 * this.length * this.width + 2 * this.length * this.height + 2 * this.width * this.height;
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.length * this.height + 2 * this.width * this.height;
    }

    public double calculateVolume() {
        return this.length * this.height * this.width;
    }
}
