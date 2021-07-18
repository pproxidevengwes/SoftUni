public class Dough {

    private static final double BASE_CALORIES_MODIFIER = 2.0;

    private String flourType;
    private String bakingTechnique;
    private double weight;

    private enum DoughTypes {

        WHITE(1.5),
        WHOLEGRAIN(1.0);


        private double modifier;

        DoughTypes(double modifier) {
            this.modifier = modifier;
        }

        private double getModifier() {
            return this.modifier;
        }
    }

    private enum BakeTypes {
        CRISPY(0.9),
        CHEWY(1.1),
        HOMEMADE(1.0);

        private double modifier;

        BakeTypes(double modifier) {
            this.modifier = modifier;
        }

        private double getModifier(){
            return this.modifier;
        }
    }


    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }


    private void setFlourType(String flourType) {
        checkDoughData(flourType);
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        checkBakingTechnique(bakingTechnique);
        this.bakingTechnique = bakingTechnique;

    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    private void checkBakingTechnique(String bakingTechnique){
        try{
            BakeTypes.valueOf(bakingTechnique.toUpperCase());
        }catch (Exception e){
            doughError();
        }
    }

    private void checkDoughData(String data) {
        try {
            DoughTypes.valueOf(data.toUpperCase());
        } catch (Exception e) {
            doughError();
        }

    }

    public double calculateCalories() {
        return (BASE_CALORIES_MODIFIER * this.weight)
                * DoughTypes.valueOf(this.flourType.toUpperCase()).getModifier()
                * BakeTypes.valueOf(this.bakingTechnique.toUpperCase()).getModifier();
    }

    private void doughError(){
        throw new IllegalArgumentException("Invalid type of dough.");
    }

}
