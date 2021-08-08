package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTests {

    private Player player;

    @Test
    public void testConstructor() {
        Player newPlayer = new Player("Teest", 123);

        assertEquals("Teest", newPlayer.getUsername());
        assertEquals(123, newPlayer.getHealth());
    }

    @Test (expected = NullPointerException.class)
    public void testNullUserNameException () {
        player = new Player("",100);
    }

    @Test (expected = NullPointerException.class)
    public void testEmptySpaceUserNameException () {
        player = new Player("   ", 99);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testHealthLessThanZero () {
        player = new Player("XXX", - 10);
    }

    @Test (expected = NullPointerException.class)
    public void testAddNullGun () {
        Gun gun = null;
        player = new Player("Tommy", 100);
        player.addGun(gun);
    }

    @Test
    public void testAddGun () {
        Gun gun = new Gun("DEagle", 21);
        player = new Player("Tommy", 100);
        player.addGun(gun);

        assertEquals(gun, player.getGun("DEagle"));
    }

    @Test
    public void testRemoveGun () {
        Gun gun = new Gun("DEagle", 21);
        player = new Player("Tommy", 100);
        player.addGun(gun);
        assertTrue(player.removeGun(gun));
    }

    @Test
    public void testGetGun () {
        Gun gun = new Gun("DEagle", 21);
        player = new Player("Tommy", 100);
        player.addGun(gun);

        Gun expected = gun;
        Gun actual = player.getGun("DEagle");
        assertEquals(expected, actual);
    }

    @Test
    public void testDamageMore() {
        player = new Player("Tommy", 100);
        player.takeDamage(50);
        assertEquals(50, player.getHealth());
    }
}
