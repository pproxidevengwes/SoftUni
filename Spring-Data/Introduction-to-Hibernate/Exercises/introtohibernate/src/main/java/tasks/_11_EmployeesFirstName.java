import entities.Employee;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import javax.persistence.Persistence;
        import java.util.Scanner;

public class _11_EmployeesFirstName {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter pattern: ");
        String pattern = scanner.nextLine();

        entityManager.createQuery(
                "SELECT e FROM Employee e " +
                        "WHERE e.firstName LIKE concat(?1, '%')", Employee.class)
                .setParameter(1, pattern)
                .getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %s - %s - ($%.2f)%n",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getJobTitle(),
                            e.getSalary());
                });

        entityManager.close();
    }
}
