package JediGalaxy_05;

public class Galaxy {
    private Star[][] stars;

    public Galaxy(int rows, int cols) {
        this.stars = new Star[rows][cols];
        this.fillStars();
    }

    public int getRows() {
        return this.stars.length;
    }

    public int getCols(int row) {
        return this.stars[row].length;
    }

    private void fillStars() {
        int starValue = 0;
        for (int row = 0; row < this.stars.length; row++) {
            for (int col = 0; col < this.stars[row].length; col++) {
                this.stars[row][col] = new Star(starValue++);
            }
        }
    }

    public void setStar(int row, int col, Star star) {
        if (isInBounds(row, col)) {
            this.stars[row][col] = star;
        }
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.stars.length && col >= 0
                && col < this.stars[row].length;
    }

    public int getStarsValue(int row, int col) {
        int result = 0;
        if (isInBounds(row, col)) {
            result = this.stars[row][col].getValue();
        }
        return result;
    }

}
