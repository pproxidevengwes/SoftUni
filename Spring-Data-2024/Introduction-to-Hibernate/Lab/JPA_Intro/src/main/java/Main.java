import entities.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("general");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();;

        Teacher teacher = new Teacher();
        teacher.setName("Geri");
        entityManager.persist(teacher);

        Teacher fromDB = entityManager.find(Teacher.class, 3);
        System.out.println(fromDB);

        entityManager.getTransaction().commit();


    }
}