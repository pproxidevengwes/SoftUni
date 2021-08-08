package CounterStriker.models.field;

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FieldImpl implements Field {
    @Override
    public String start(Collection<Player> players) {
        List<Player> playersInBattle = new ArrayList<>(players);
        boolean terroristWin = false;
        boolean counterTerroristWin = false;
        while (!terroristWin && !counterTerroristWin) {
            battle(playersInBattle, "Terrorist", "CounterTerrorist");
            battle(playersInBattle, "CounterTerrorist", "Terrorist");
            if (playersInBattle.stream().noneMatch(player ->
                    player.getClass().getSimpleName().equals("Terrorist") && player.isAlive())) {
                counterTerroristWin = true;
            }
            if (playersInBattle.stream().noneMatch(player ->
                    player.getClass().getSimpleName().equals("CounterTerrorist") && player.isAlive())) {
                terroristWin = true;
            }
        }

        if (terroristWin){
            return "Terrorist wins!";
        }
        return "Counter Terrorist wins!";
    }

    private void battle(List<Player> playersInBattle, String attack, String defend) {
        for (int i = 0; i < playersInBattle.size(); i++) {
            Player attacker = playersInBattle.get(i);;
            if (attacker.getClass().getSimpleName().equals(attack)&&attacker.isAlive()){
                for (Player victim : playersInBattle) {
                    if (victim.getClass().getSimpleName().equals(defend) && victim.isAlive()) {
                        int damage = attacker.getGun().fire();
                        victim.takeDamage(damage);
                    }
                }

            }

        }
    }
}
