package computers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComputerManagerTests {
    private Computer computer;
    private List<Computer> computers;
    private ComputerManager computerManager;

    @Before
    public void setUp() {
        computer = new Computer("Apple", "MakBook", 2000);
        computerManager = new ComputerManager();
        computerManager.addComputer(computer);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputersShouldReturnUnmodifiableList() {
        computerManager.getComputers().remove(computer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerShouldThExWhenComputerExist() {
        computerManager.addComputer(computer);
    }

    @Test
    public void testAddComputerShouldAddComputerWhenNotExist() {
        computerManager.addComputer(new Computer("Asus", "TUF", 1500));
        assertEquals(2, computerManager.getCount());
    }

    @Test
    public void testRemoveComputerShouldRemoveComputerWhenExist() {
        computerManager.removeComputer("Apple", "MacBook");
        assertEquals(0, computerManager.getCount());
    }

    @Test
    public void testRemoveComputerShouldReturnRemovedComputerWhenExist() {
        Computer expected = computer;
        Computer actual = computerManager.removeComputer("Apple", "MacBook");
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveComputerShouldThExWhenComputerNotExist() {
        computerManager.removeComputer("Acer", "Nitro");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputersByManufacturerShouldThExWhenIsNull() {
        computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void testGetComputersByManufacturerShouldReturnListOfComputers() {
        List<Computer> expected = new ArrayList<>();
        expected.add(computer);
        List<Computer> actual = computerManager.getComputersByManufacturer("Apple");
        assertEquals(expected, actual);
    }
}
