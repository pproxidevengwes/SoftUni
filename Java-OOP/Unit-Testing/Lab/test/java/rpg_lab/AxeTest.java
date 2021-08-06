package test.java.rpg_lab;

import main.java.rpg_lab.Axe;
import main.java.rpg_lab.Dummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTest {

    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 2;
    private static final int DUMMY_HEALTH = 11;
    private static final int DUMMY_EXPERIENCE = 13;
    private static final int EXPECTED_DURABILITY = AXE_DURABILITY - 1;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void setUp() {
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void weaponAttacksLosesDurability() {
        this.axe.attack(dummy);

        Assert.assertEquals(EXPECTED_DURABILITY, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void brokenWeaponCantAttack() {
        this.axe.attack(dummy);
        this.axe.attack(dummy);
        this.axe.attack(dummy);
    }
}
