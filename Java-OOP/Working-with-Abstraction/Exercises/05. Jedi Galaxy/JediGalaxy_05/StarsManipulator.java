package JediGalaxy_05;

public class StarsManipulator {
    private Galaxy galaxy;

    public StarsManipulator(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public void destroyStars(int[] enemyPosition) {
        int enemyRow = enemyPosition[0];
        int enemyCol = enemyPosition[1];
        while (enemyRow >= 0 && enemyCol >= 0) {
            if (isInBounds(enemyRow, enemyCol)) {
                this.galaxy.setStar(enemyRow, enemyCol, new Star(0));
            }
            enemyRow--;
            enemyCol--;
        }

    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.galaxy.getRows() && col >= 0
                && col < this.galaxy.getCols(row);
    }

    public long sumOfStars(int[] playerPositions) {
        int row = playerPositions[0];
        int col = playerPositions[1];
        long sum = 0;
        while (row >= 0 && col < this.galaxy.getCols(0)) {
            if (isInBounds(row, col)) {
                sum += this.galaxy.getStarsValue(row, col);
            }
            row--;
            col++;
        }
        return sum;

    }

}
