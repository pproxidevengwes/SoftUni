package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    private static final double INITIAL_OXYGEN = 70;
    private static final double DECREASE_OXYGEN = 5;


    public Biologist(String name) {
        super(name, INITIAL_OXYGEN);
    }

    @Override
    public void breath() {
        this.setOxygen(this.getOxygen() - DECREASE_OXYGEN);
        if(this.getOxygen() < 0){
            this.setOxygen(0);
        }
    }
}
