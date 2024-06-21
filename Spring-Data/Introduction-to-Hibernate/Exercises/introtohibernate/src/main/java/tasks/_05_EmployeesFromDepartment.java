import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _05_EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(
                        "SELECT e FROM Employee e" +
                                " WHERE e.department.name = :departmentName" +
                                " ORDER BY e.salary ASC, e.id ASC", Employee.class)
                .setParameter("departmentName", "Research and Development")
                .getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %s from %s - $%.2f%n",
                            e.getFirstName(), e.getLastName(),
                            e.getDepartment(), e.getSalary());
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
