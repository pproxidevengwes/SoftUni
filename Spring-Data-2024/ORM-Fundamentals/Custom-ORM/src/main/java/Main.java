import entities.Order;
import entities.Product;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        MyConnector.createConnnection("root", "", "mini_orm");
        Connection connection = MyConnector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        User user = userEntityManager.findFirst(User.class);
        userEntityManager.delete(user);



//        EntityManager<User> userEntityManager = new EntityManager<>(connection);
//        User user = new User("Katrin", 29, LocalDate.now());
//        user.setEmail("j45@abv.bg");
//        userEntityManager.doAlter(user);
//        userEntityManager.persist(user);

//        EntityManager<User> userEntityManager = new EntityManager<>(connection);
//        userEntityManager.doCreate(User.class);

//        User users = userEntityManager.findFirst(User.class);
//        System.out.println(users);
//        Iterable<User> users = userEntityManager.find(User.class, "age>40");
//        System.out.println(users.iterator().next());

//        EntityManager<Product> productEntityManager = new EntityManager<>(connection);
//        Product pr = new Product("pen", 2.39);
//        productEntityManager.persist(pr);
//
//        EntityManager<Order> orderEntityManager = new EntityManager<>(connection);
//        orderEntityManager.doCreate(Order.class);
//        Order order = new Order("145b44", LocalDate.now());
//        orderEntityManager.persist(order);

    }
}
