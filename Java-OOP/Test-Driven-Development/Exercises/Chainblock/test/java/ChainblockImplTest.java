import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChainblockImplTest {
    private Chainblock chainblock;
    private List<Transaction> transactionList;

    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();
        this.createTransactions();
    }

    private void createTransactions() {
        this.transactionList = new ArrayList<>();
        Transaction one = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                "From", "To", 10.50);
        Transaction two = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                "From2", "To2", 10.60);
        Transaction three = new TransactionImpl(1, TransactionStatus.FAILED,
                "From3", "To3", 10.70);
        Transaction four = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                "From4", "To4", 10.80);
        this.transactionList.add(one);
        this.transactionList.add(two);
        this.transactionList.add(three);
        this.transactionList.add(four);
    }

    @Test

    public void testContainsById() {
        chainblock.add(transactionList.get(0));
        Assert.assertTrue(chainblock.contains(transactionList.get(0).getId()));

    }

    @Test

    public void testAddMethod() {
        chainblock.add(transactionList.get(0));
        Assert.assertEquals(1, chainblock.getCount());

    }

    @Test
    public void testAddUniqueId() {

        chainblock.add(transactionList.get(0));
        Assert.assertEquals(1, chainblock.getCount());
        chainblock.add(transactionList.get(0));
        Assert.assertEquals(1, chainblock.getCount());

    }

    @Test

    public void testTransactionStatus() {
        chainblock.add(transactionList.get(0));
        chainblock.changeTransactionStatus(transactionList.get(0).getId(), TransactionStatus.FAILED);
        Assert.assertEquals(TransactionStatus.FAILED, transactionList.get(0).getStatus());

    }

    @Test(expected = IllegalArgumentException.class)

    public void testTransactionsThrowEx() {
        chainblock.changeTransactionStatus(transactionList.get(0).getId(), TransactionStatus.FAILED);
    }

    @Test

    public void testRemoveGivenId() {
        chainblock.add(transactionList.get(0));
        chainblock.add(transactionList.get(1));

        chainblock.removeTransactionById(transactionList.get(0).getId());
        Assert.assertEquals(1, chainblock.getCount() + 1);


    }


    @Test(expected = IllegalArgumentException.class)

    public void testRemoveThrowEx() {
        chainblock.add(transactionList.get(0));
        chainblock.removeTransactionById(5);

    }

    @Test

    public void testPresentGetId() {
        chainblock.add(transactionList.get(0));
        chainblock.add(transactionList.get(1));

        chainblock.getById(transactionList.get(0).getId());
        Assert.assertEquals(1, transactionList.get(0).getId());

    }

    @Test(expected = IllegalArgumentException.class)

    public void testNotPresentId() {
        chainblock.getById(transactionList.get(1).getId());

    }

    @Test(expected = IllegalArgumentException.class)

    public void testNotPresentTransactions() {
        chainblock.add(transactionList.get(0));
        chainblock.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test

    public void testIfReturnGivenTyeOfTransactions() {
        chainblock.add(transactionList.get(0));
        chainblock.add(transactionList.get(1));
        chainblock.add(transactionList.get(2));
        chainblock.add(transactionList.get(3));

        List<Transaction> expected = this.transactionList
                .stream()
                .filter(t -> t.getStatus() == TransactionStatus.SUCCESSFUL)
                .collect(Collectors.toList());

        Iterable<Transaction> result = chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);

        List<Transaction> actual = new ArrayList<>();

        result.forEach(actual::add);

        Assert.assertEquals(3, expected.size());

        for (Transaction transaction : actual) {
            Assert.assertEquals(TransactionStatus.SUCCESSFUL, transaction.getStatus());
        }


    }


}
