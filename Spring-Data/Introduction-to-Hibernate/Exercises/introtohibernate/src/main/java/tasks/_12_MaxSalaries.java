import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@SuppressWarnings("unchecked")
public class _12_MaxSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        List<Object[]> rows = entityManager.createNativeQuery(
                        "SELECT d.name, max(e.salary) as max_salary " +
                                "FROM departments d " +
                                "JOIN employees e ON d.department_id = e.department_id " +
                                "GROUP BY d.name " +
                                "HAVING max_salary NOT BETWEEN 30000 AND 70000;")
                .getResultList();
      
        rows.forEach(row -> {
            System.out.printf("%s - %.2f%n", row[0], row[1]);
        });
       

        entityManager.close();
    }
}
