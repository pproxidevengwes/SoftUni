package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

public class Terrorist extends PlayerImpl{
    public Terrorist(String username, int health, int armor, Gun gun) {
        super(username, health, armor, gun);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName() +":" + " " + this.getUsername())
                .append(System.lineSeparator())
                .append("--Health: " + this.getHealth())
                .append(System.lineSeparator())
                .append("--Armor: " + this.getArmor())
                .append(System.lineSeparator())
                .append("--Gun: " + this.getGun().getName())
                .append(System.lineSeparator());

        return sb.toString().trim();
    }
}
