package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;
import javax.naming.OperationNotSupportedException;
import static org.junit.Assert.*;

public class DatabaseTest {

    Database database;
    private static final Person[] PERSONS = new Person[]{
            new Person(1 , "A"),
            new Person(2 , "B"),
            new Person(3 , "C")
    };

    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(PERSONS);
    }

    @Test
    public void testCtorCorrectlyElements(){
        assertEquals(PERSONS.length , database.getElements().length);
        assertArrayEquals(PERSONS , database.getElements());
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testCtorWithoutElements() throws OperationNotSupportedException {
        database = new Database();
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testCtorWithMoreThan16Elements() throws OperationNotSupportedException {
        database = new Database(new Person[17]);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testAddElementNullThrowException() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testCorrectAddOneElement() throws OperationNotSupportedException {
        database.add(new Person(4 , "D"));
        assertEquals(PERSONS.length + 1 , database.getElements().length);
    }

    @Test
    public void whenRemoveElementsRemovedLastElements() throws OperationNotSupportedException {
        database.remove();
        assertEquals(PERSONS.length - 1 , database.getElements().length);
        assertEquals(PERSONS[PERSONS.length - 2] , database.getElements()[database.getElements().length - 1]);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void whenRemoveFromEmptyDatabaseThrowsException() throws OperationNotSupportedException {
        database.remove();
        database.remove();
        database.remove();
        database.remove();
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByUsernameWithNullThrowException() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByUsernameWithWrongUsernameThrowException() throws OperationNotSupportedException {
        database.findByUsername("Pesho");
    }

    @Test
    public void findByUsernameWithCorrectName() throws OperationNotSupportedException {
        Person person = PERSONS[0];
        Person byUsername = database.findByUsername(person.getUsername());

        assertEquals(person.getUsername() , byUsername.getUsername());
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByIdWithWrongIdThrowException() throws OperationNotSupportedException {
        database.findById(123);
    }

    @Test
    public void findByIdWithCorrectId() throws OperationNotSupportedException {
        Person person = PERSONS[0];
        Person byUsername = database.findById(person.getId());

        assertEquals(person.getId() , byUsername.getId());
    }
}
