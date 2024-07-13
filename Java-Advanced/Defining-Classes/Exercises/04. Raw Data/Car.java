public class Car {
   private String model;
   private Engine engine;
   private Cargo cargo;
    Tire[]tires=new Tire[4];

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public Tire[] getTires() {
        return tires;
    }

    public Car(String model, Engine engine, Cargo cargo, Tire[] tires) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tires = tires;
    }
}
