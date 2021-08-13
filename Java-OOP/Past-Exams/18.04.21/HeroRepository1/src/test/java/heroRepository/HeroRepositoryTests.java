package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    private HeroRepository heroRepository;
    private Hero hero;

    @Before
    public void setUp() throws Exception {
        this.heroRepository = new HeroRepository();
        this.hero = new Hero("Annie", 11);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateIsNull() {
        this.heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithDuplicateName() {
        this.heroRepository.create(this.hero);
        this.heroRepository.create(this.hero);
    }

    @Test
    public void testCreateSuccessfulHero() {
        Assert.assertEquals(0, this.heroRepository.getCount());

        this.heroRepository.create(this.hero);
        Assert.assertEquals(1, this.heroRepository.getCount());

        Hero createdHero = this.heroRepository.getHero("Annie");

        Assert.assertEquals(createdHero.getName(), this.hero.getName());
        Assert.assertEquals(createdHero.getLevel(), this.hero.getLevel());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNullName() {
        this.heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithEmptyName() {
        this.heroRepository.remove("");
    }

    @Test
    public void testRemoveSuccessfully() {
        Assert.assertEquals(0, this.heroRepository.getCount());

        this.heroRepository.create(this.hero);
        Assert.assertEquals(1, this.heroRepository.getCount());
        this.heroRepository.remove("Annie");

        Assert.assertEquals(0, this.heroRepository.getCount());
        Hero removedHero = this.heroRepository.getHero("Annie");

        Assert.assertNull(removedHero);
    }

    @Test
    public void testGetHeroWithHighestLevel() {
        Hero hero1 = new Hero("Jinx", 9);
        Hero hero2 = new Hero("Ahri", 16);
        Hero hero3 = new Hero("Teemo", 7);

        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero3);

        Assert.assertEquals(3, this.heroRepository.getHeroes().size());
        Hero highest = this.heroRepository.getHeroWithHighestLevel();

        Assert.assertEquals(highest.getName(), hero2.getName());
        Assert.assertEquals(highest.getLevel(), hero2.getLevel());
    }
}
