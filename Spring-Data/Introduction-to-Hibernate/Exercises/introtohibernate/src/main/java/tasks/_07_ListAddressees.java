import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _07_ListAddressees {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(
                        "SELECT a FROM Address a " +
                                "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(a -> {
                    System.out.printf("%s, %s - %d employees%n",
                            a.getText(),
                            a.getTown().getName(),
                            a.getEmployees().size());
                });

        entityManager.close();
    }
}
