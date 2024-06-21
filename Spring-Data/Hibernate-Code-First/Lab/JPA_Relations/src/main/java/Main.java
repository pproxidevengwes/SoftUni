import entities.shampoo.BasicIngredient;
import entities.shampoo.BasicLabel;
import entities.shampoo.BasicShampoo;
import entities.shampoo.ProductionBatch;
import entities.vehicles.Bike;
import entities.vehicles.Car;
import entities.vehicles.Truck;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("CodeFirstLab");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        /* vehicles table
        Bike bike = new Bike(21);
        Car car = new Car(5);
        Truck truck = new Truck(2500,40);


        entityManager.persist(bike);
        entityManager.persist(car);
        entityManager.persist(truck);
         */

        BasicIngredient ingredient = new BasicIngredient(100, "B12");
        BasicIngredient ingredient2 = new BasicIngredient(12, "E120");

        ProductionBatch batch = new ProductionBatch(LocalDate.now());
        BasicLabel label = new BasicLabel("blue");
        BasicShampoo shampoo = new BasicShampoo("shower", label, batch);

        shampoo.addIngredient(ingredient);
        shampoo.addIngredient(ingredient2);

        entityManager.persist(ingredient);
        entityManager.persist(ingredient2);

        entityManager.persist(batch);
        entityManager.persist(label);
        entityManager.persist(shampoo);

        ProductionBatch productionBatch = entityManager.find(ProductionBatch.class, 1);

        //Set<BasicShampoo> shampoos = productionBatch.getShampoos();
        //shampoos.forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
