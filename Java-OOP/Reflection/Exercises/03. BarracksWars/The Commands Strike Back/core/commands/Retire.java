package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import barracksWars.models.units.AbstractUnit;
import jdk.jshell.spi.ExecutionControl;

public class Retire extends Command {

    public Retire(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute()  {
        String unitType = super.getData()[1];
        super.getRepository().removeUnit(unitType);
        return unitType + " retired!";
    }
}
