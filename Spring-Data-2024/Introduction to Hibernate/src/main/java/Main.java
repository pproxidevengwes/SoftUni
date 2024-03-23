import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student student = new Student();
        student.setName("Gosho");
        session.save(student);


//        Student student = session.get(Student.class,1);
//        System.out.printf("%d - %s", student.getId(), student.getName());
//
//        student.setName("Updated");

        List<Student> students = session.createQuery("FROM Student", Student.class).list();

        for (Student current : students) {
            System.out.println(current);

        }

        session.getTransaction().commit();
        session.close();

    }
}
