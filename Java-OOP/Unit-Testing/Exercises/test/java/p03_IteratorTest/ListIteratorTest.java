package p03_IteratorTest;

import org.junit.Test;
import javax.naming.OperationNotSupportedException;
import static org.junit.Assert.*;

public class ListIteratorTest {

    @Test (expected = OperationNotSupportedException.class)
    public void testConstructorWithNullElements() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testWithCorrectElements() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("pesho", "ivan", "gosho");
        assertTrue(listIterator.move());

    }

    @Test
    public void testMoveAfterLastElements() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("pesho", "ivan", "gosho");
        assertTrue(listIterator.move());
        assertTrue(listIterator.move());
        assertFalse(listIterator.move());
    }

    @Test (expected = IllegalStateException.class)
    public void testPrintWithEmptyListThrowException() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator();
        listIterator.print();
    }

    @Test
    public void testPrintWithElements() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("pesho" , "ivan");
        String print = listIterator.print();
        assertEquals("pesho" , print);
    }
}
