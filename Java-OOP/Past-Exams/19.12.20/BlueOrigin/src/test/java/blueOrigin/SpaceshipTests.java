package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    private static final String SPACESHIP_NAME = "Genesis";
    private static final int SPACESHIP_CAPACITY = 2;
    private static final String NAME_BY_ASTRONAUT_1 = "Armstrong";
    private static final String NAME_BY_ASTRONAUT_2 = "Dobrovolsky";
    private static final double OXYGEN_BY_ASTRONAUT_1 = 55.0;
    private static final double OXYGEN_BY_ASTRONAUT_2 = 100.0;

    private static Spaceship spaceship;
    private static Astronaut astronaut1;
    private static Astronaut astronaut2;

    @Before
    public void setUp() {
        astronaut1 = createAstronaut(NAME_BY_ASTRONAUT_1, OXYGEN_BY_ASTRONAUT_1);
        astronaut2 = createAstronaut(NAME_BY_ASTRONAUT_2, OXYGEN_BY_ASTRONAUT_2);
        spaceship = createSpaceship(SPACESHIP_NAME, SPACESHIP_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMustFailsWhenGetCapacityLessThenZero() {

        spaceship = createSpaceship(SPACESHIP_NAME, -2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorMustFailsWhenGetInvalidName() {

        spaceship = createSpaceship("", SPACESHIP_CAPACITY);
    }

    @Test
    public void testConstructorMustCreateObjectWhenParametersIsValid() {

        Assert.assertEquals(SPACESHIP_NAME, spaceship.getName());
        Assert.assertEquals(SPACESHIP_CAPACITY, spaceship.getCapacity());
    }

    @Test
    public void testAddAstronautWhitCorrectParameters() {

        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        Assert.assertEquals(2, spaceship.getCount());
        Assert.assertEquals(NAME_BY_ASTRONAUT_1, astronaut1.getName());
        Assert.assertEquals(OXYGEN_BY_ASTRONAUT_1, astronaut1.getOxygenInPercentage(), 0.00);
        Assert.assertEquals(NAME_BY_ASTRONAUT_2, astronaut2.getName());
        Assert.assertEquals(OXYGEN_BY_ASTRONAUT_2, astronaut2.getOxygenInPercentage(), 0.00);
    }

    @Test
    public void testRemoveAstronautMustRemoveCorrectObjectAndReturnBoolean() {

        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        Assert.assertTrue(spaceship.remove(NAME_BY_ASTRONAUT_1));
        Assert.assertFalse(spaceship.remove(NAME_BY_ASTRONAUT_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAstronautMustFailsWhenAddMoreThenCapacity() {

        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        spaceship.add(createAstronaut("name", 44.4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAstronautMustFailsWhenTryToAddAstronautWhitExistingName() {

        spaceship.add(astronaut1);
        spaceship.add(astronaut1);
    }

    private static Spaceship createSpaceship(String name, int capacity) {
        return new Spaceship(name, capacity);
    }

    private static Astronaut createAstronaut(String name, double oxygen) {
        return new Astronaut(name, oxygen);
    }
}
