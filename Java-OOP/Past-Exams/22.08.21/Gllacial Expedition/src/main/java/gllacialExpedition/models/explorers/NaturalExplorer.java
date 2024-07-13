package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double INITIAL_UNITS_ENERGY = 60;
    private static final double DECREASE_ENERGY = 7;

    public NaturalExplorer(String name) {
        super(name, INITIAL_UNITS_ENERGY);
    }

    @Override
    public void search() {
        double newEnergy = this.getEnergy() - DECREASE_ENERGY;
        if (newEnergy < 0){
            this.setEnergy(0);
        } else {
            this.setEnergy(newEnergy);
        }

    }
}
