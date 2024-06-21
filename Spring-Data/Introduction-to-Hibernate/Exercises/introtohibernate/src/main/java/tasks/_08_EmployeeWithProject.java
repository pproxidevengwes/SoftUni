import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _08_EmployeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.find(Employee.class, id);

        System.out.printf("%s %s - %s%n",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects()
                .stream()
                .map(Project::getName)
                .sorted()
                .forEach(p -> System.out.printf("\t%s%n", p));

        entityManager.close();
    }
}
