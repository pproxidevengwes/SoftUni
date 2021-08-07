import java.util.*;

public class ChainblockImpl implements Chainblock {

    private Map<Integer, Transaction> transactionById;

    public ChainblockImpl(Map<Integer, Transaction> transactionById) {
        transactionById = new HashMap<>();
    }

    public ChainblockImpl() {
        transactionById = new HashMap<>();
    }


    public int getCount() {
        return this.transactionById.size();
    }

    public void add(Transaction transaction) {
        if (!contains(transaction.getId())) {
            this.transactionById.put(transaction.getId(), transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return contains(transaction.getId());
    }

    public boolean contains(int id) {
        return this.transactionById.containsKey(id);
    }


    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!contains(id)) {
            throw new IllegalArgumentException();
        }
        this.transactionById.get(id).setStatus(newStatus);

    }

    public void removeTransactionById(int id) {
        if (!contains(id)) {

            throw new IllegalArgumentException();
        } else {
            this.transactionById.remove(id);
        }


    }

    public Transaction getById(int id) {
        if (!contains(id)) {
            throw new IllegalArgumentException();

        } else {
            return this.transactionById.get(id);
        }
    }


    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction value : transactionById.values()) {
            if (value.getStatus() == status) {
                transactions.add(value);
            }
        }

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }

        transactions.sort(Comparator.comparing(Transaction::getAmount).reversed());

        return transactions;
    }


    public Iterator<Transaction> iterator() {
        return null;
    }
}
