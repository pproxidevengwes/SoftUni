package spaceStation.models.astronauts;

import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

public abstract class BaseAstronaut implements Astronaut{
    private String name;
    private double oxygen;
    private Bag bag;
    private static final double DECREASE_OXYGEN = 10;

    protected BaseAstronaut(String name, double oxygen){
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
    }

    private void setName(String name){
        if(name.trim().isEmpty() || name == null){
            throw new NullPointerException (ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setOxygen(double oxygen){
        if(oxygen < 0){
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }




    public String getName() {
        return this.name;
    }

    public double getOxygen() {
        return this.oxygen;
    }

    public boolean canBreath() {

        if(this.getOxygen() > 0){
            return true;
        }
        return false;
    }

    public Bag getBag() {
        return this.bag;
    }

    public void breath() {
        this.setOxygen(this.getOxygen() - DECREASE_OXYGEN);
        if(this.getOxygen() < 0){
            this.setOxygen(0);
        }
    }

    @Override
    public String toString(){
        StringBuilder fill = new StringBuilder();
        fill.append(String.format("Name: %s", this.getName())).append(System.lineSeparator());
        fill.append(String.format("Oxygen: %.0f", this.getOxygen())).append(System.lineSeparator());

        fill.append("Bag items: " );
        if(this.getBag().getItems().isEmpty()){
            fill.append("none");
        }else {
            fill.append(String.join(", ", this.getBag().getItems()));
        }
        return fill.toString();
    }
}
