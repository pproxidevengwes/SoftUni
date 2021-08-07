package p01_Database;

import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testCtorCorrectlyElements() throws OperationNotSupportedException {
        Database database = new Database(1,2,3,4,5,6,7,8,9,10);
        assertEquals(10 , database.getElements().length);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testCtorThrowExceptionWithZeroElements() throws OperationNotSupportedException {
        Database database = new Database();
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testAddElementNullThrowException() throws OperationNotSupportedException {
        Database database = new Database(1 , 2);
        database.add(null);
    }

    @Test
    public void testCorrectAddOneElement() throws OperationNotSupportedException {
        Database database = new Database(1 , 2);
        database.add(3);
        assertEquals(3 , database.getElements().length);
        assertEquals(3 , (long)database.getElements()[2]);
    }
}
