import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inheritance");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    }
}
