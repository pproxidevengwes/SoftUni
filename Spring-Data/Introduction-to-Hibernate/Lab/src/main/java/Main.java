import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student("emo", 23);
        entityManager.persist(student);
        Student second = new Student("pesho", 44);
        entityManager.persist(second);

        Teacher teacher = new Teacher("petka", LocalDate.now());
        entityManager.persist(teacher);

        teacher.setName("gergana");
        entityManager.persist(teacher);

        entityManager.getTransaction().commit();
        entityManager.clear();
    }
}
