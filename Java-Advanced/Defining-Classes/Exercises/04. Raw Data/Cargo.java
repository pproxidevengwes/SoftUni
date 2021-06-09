public class Cargo {
   private int weight;
   private String cargoType;

    public String getCargoType() {
        return cargoType;
    }

    public Cargo(int weight, String type) {
        this.weight = weight;
        this.cargoType = type;
    }
}
