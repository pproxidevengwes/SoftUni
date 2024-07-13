import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class _09_Latest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        List<Project> projects = entityManager.createQuery(
                        "SELECT p FROM Project p " +
                                "ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        projects.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("Project name: %s%n" +
                                    " \tProject Description: %s%n" +
                                    " \tProject Start Date:%s%n" +
                                    " \tProject End Date: %s%n",
                            p.getName(),
                            p.getDescription(),
                            p.getStartDate().minusHours(3).format(formatter),
                            p.getEndDate());
                });

        entityManager.close();
    }
}
