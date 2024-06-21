package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroTest {
    private final static int EXPERIENCE = 45;

    private Hero hero;

    @Before
    public void setUp() {
        Weapon weapon = mock(Weapon.class);
        this.hero = new Hero("Gandalf", weapon);
    }

    @Test
    public void testHeroGainsExperienceWhenTargetIsKilled() {
        Target target = mock(Target.class);
        when(target.giveExperience()).thenReturn(EXPERIENCE);
        when(target.isDead()).thenReturn(true);

        this.hero.attack(target);
        assertEquals(EXPERIENCE, this.hero.getExperience());
    }

}
