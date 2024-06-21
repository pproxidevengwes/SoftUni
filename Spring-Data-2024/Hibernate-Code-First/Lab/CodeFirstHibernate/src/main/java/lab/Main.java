package lab;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab.composition.Company;
import lab.composition.PlateNumber;
import lab.inheritance.*;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        persistAll(entityManager);

        find(entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void find(EntityManager entityManager) {
        Company company = entityManager.find(Company.class, 1);
        List<Plane> planes = company.getPlanes();
        for (Plane plane : planes) {
            System.out.println(plane);
        }
    }

    private static void persistAll(EntityManager entityManager) {
        PlateNumber plateNumber = new PlateNumber("6350");

        Company company = new Company("Airl1ne1");

        Vehicle car = new Car("Corsa", BigDecimal.TEN, "Petrol", 5, plateNumber);
        Vehicle bike = new Bike("BMX", BigDecimal.ONE, "None");
        Plane plane = new Plane("Boeing", BigDecimal.TEN, "PlaneFuel", 100, company);
        Vehicle truck = new Truck("Scania", BigDecimal.ONE, "Diesel", 40);


        entityManager.persist(company);
        entityManager.persist(plateNumber);
        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);
        entityManager.persist(truck);
    }
}
