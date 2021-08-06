package test.java.rpg_lab;

import main.java.rpg_lab.Dummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {

    private static final int DUMMY_HEALTH = 11;
    private static final int DUMMY_EXPERIENCE = 13;
    private static final int ATTACK = 5;

    private Dummy aliveDummy;
    private Dummy deadDummy;

    @Before
    public void setUp() {
        this.aliveDummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
        this.deadDummy = new Dummy(0, DUMMY_EXPERIENCE);
    }


    @Test
    public void attackedTargetLosesHealth() {
        aliveDummy.takeAttack(ATTACK);
        Assert.assertEquals(DUMMY_HEALTH - ATTACK, aliveDummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void dummyThrowsExceptionWhenAttacked() {

        deadDummy.takeAttack(ATTACK);
    }

    @Test
    public void testDeadDummyCanGiveExperience() {
        int actualExperience = deadDummy.giveExperience();
        Assert.assertEquals(DUMMY_EXPERIENCE, actualExperience);
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyCantGiveExperience() {

        aliveDummy.giveExperience();
    }
}
