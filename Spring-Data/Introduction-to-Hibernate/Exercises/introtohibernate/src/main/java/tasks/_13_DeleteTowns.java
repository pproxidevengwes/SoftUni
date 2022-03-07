import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _13_DeleteTowns {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.print("Enter town: ");
        Scanner scanner = new Scanner(System.in);
        String town = scanner.nextLine();

        List<Address> addresses = entityManager.createQuery(
                        "SELECT a FROM Address a " +
                                "WHERE a.town.name = ?1", Address.class)
                .setParameter(1, town)
                .getResultList();

        int deleted = addresses.size();

        for (Address a : addresses) {
            entityManager.remove(a);
        }
        System.out.printf("%d address in %s deleted%n", deleted, town);

        entityManager.close();
    }
}

