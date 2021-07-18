
public class Topping {
    private static final double BASE_CALORIES_MODIFIER = 2.0;

    private String toppingName;
    private double weight;

    private enum ToppingsTypes {
        MEAT(1.2),
        VEGGIES(0.8),
        CHEESE(1.1),
        SAUCE(0.9);

        private double modifier;

        ToppingsTypes(double modifier) {
            this.modifier = modifier;
        }

        private double getModifier() {
            return this.modifier;
        }
    }

    public Topping(String toppingName, double weight) {
        setToppingName(toppingName);
        setWeight(weight);
    }

    private void setToppingName(String toppingName) {
        checkToppingData(toppingName);
        this.toppingName = toppingName;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalStateException(this.toppingName + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    private void checkToppingData(String toppingName) {
        try {
            ToppingsTypes.valueOf(toppingName.toUpperCase());
        } catch (Exception e) {
            throw new IllegalStateException("Cannot place " + toppingName + " on top of your pizza.");
        }
    }

    public double calculateCalories() {
        return (BASE_CALORIES_MODIFIER * this.weight)
                * ToppingsTypes.valueOf(this.toppingName.toUpperCase()).getModifier();
    }
}
