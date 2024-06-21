package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
        private static final String FARM_NAME = "Blueberry Hill";
        private static final int FARM_CAPACITY = 2;
        private static final String TYPE_ANIMAL_1 = "Goose";
        private static final String TYPE_ANIMAL_2 = "Pig";
        private static final double ENERGY_BY_ANIMAL_1 = 55.0;
        private static final double ENERGY_BY_ANIMAL_2 = 100.0;

        private static Farm farm;
        private static Animal a1;
        private static Animal a2;

        @Before
        public void setUp() {
            a1 = createAnimal(TYPE_ANIMAL_1, ENERGY_BY_ANIMAL_1);
            a2 = createAnimal(TYPE_ANIMAL_2, ENERGY_BY_ANIMAL_2);
            farm = createFarm(FARM_NAME, FARM_CAPACITY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testConstructorMustFailsWhenGetCapacityLessThenZero() {

            farm = createFarm(FARM_NAME, -2);
        }

        @Test(expected = NullPointerException.class)
        public void testConstructorMustFailsWhenGetInvalidName() {

            farm = createFarm("", FARM_CAPACITY);
        }

        @Test
        public void testConstructorMustCreateObjectWhenParametersIsValid() {

            Assert.assertEquals(FARM_NAME, farm.getName());
            Assert.assertEquals(FARM_CAPACITY, farm.getCapacity());
        }

        @Test
        public void testAddAstronautWhitCorrectParameters() {

            farm.add(a1);
            farm.add(a2);
            Assert.assertEquals(2, farm.getCount());
            Assert.assertEquals(TYPE_ANIMAL_1, a1.getType());
            Assert.assertEquals(ENERGY_BY_ANIMAL_1, a1.getEnergy(), 0.00);
            Assert.assertEquals(TYPE_ANIMAL_2, a2.getType());
            Assert.assertEquals(ENERGY_BY_ANIMAL_2, a2.getEnergy(), 0.00);
        }

        @Test
        public void testRemoveAstronautMustRemoveCorrectObjectAndReturnBoolean() {

            farm.add(a1);
            farm.add(a2);
            Assert.assertTrue(farm.remove(TYPE_ANIMAL_1));
            Assert.assertFalse(farm.remove(TYPE_ANIMAL_1));
        }

        @Test(expected = IllegalArgumentException.class)
        public void testAddAstronautMustFailsWhenAddMoreThenCapacity() {

            farm.add(a1);
            farm.add(a2);
            farm.add(createAnimal("name", 44.4));
        }

        @Test(expected = IllegalArgumentException.class)
        public void testAddAstronautMustFailsWhenTryToAddAstronautWhitExistingName() {

            farm.add(a1);
            farm.add(a1);
        }

        private static Farm createFarm(String name, int capacity) {
            return new Farm(name, capacity);
        }

        private static Animal createAnimal(String type, double energy) {
            return new Animal(type, energy);
        }
    }

