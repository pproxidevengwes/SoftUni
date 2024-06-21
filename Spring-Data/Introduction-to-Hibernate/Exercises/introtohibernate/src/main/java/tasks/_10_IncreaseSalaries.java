import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class _10_IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(
                        "UPDATE Employee e " +
                                "SET e.salary = e.salary * 1.12 " +
                                "WHERE e.department.id IN (1,2,4,11)")
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.department.name IN ('Engineering','Tool Design','Marketing','Information Services')", Employee.class)
                .getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %s ($%.2f)%n",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getSalary());
                });

        entityManager.close();

    }
}
