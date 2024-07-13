package inheritance;

import inheritance.entities.DemoBike;
import inheritance.entities.DemoCar;
import inheritance.entities.DemoTruck;
import inheritance.entities.DemoVehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        DemoVehicle car = new DemoCar();
        DemoVehicle bike = new DemoBike();
        DemoVehicle truck = new DemoTruck();

        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();

    }
}
