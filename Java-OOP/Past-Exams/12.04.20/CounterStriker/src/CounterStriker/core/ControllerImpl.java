package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;
import CounterStriker.repositories.Repository;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Gun> guns;
    private Repository<Player> players;
    private FieldImpl field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name, bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }
        guns.add(gun);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, gun.getName());
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        if (this.guns.findByName(gunName) == null) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }
        Gun gun = this.guns.findByName(gunName);
        Player player;
        switch (type) {
            case "CounterTerrorist":
                player = new CounterTerrorist(username, health, armor, gun);
                break;
            case "Terrorist":
                player = new Terrorist(username, health, armor, gun);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        this.players.add(player);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, player.getUsername());
    }

    @Override
    public String startGame() {
        Collection<Player> alivePlayers = players.getModels().stream()
                .filter(Player::isAlive).collect(Collectors.toList());
        return this.field.start(alivePlayers);
    }

    @Override
    public String report() {
        return this.players.getModels().stream()
                .sorted(Comparator.comparing(Player::getUsername))
                .sorted(Comparator.comparingInt(Player::getHealth).reversed())
                .sorted(Comparator.comparing(f -> f.getClass().getSimpleName()))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
