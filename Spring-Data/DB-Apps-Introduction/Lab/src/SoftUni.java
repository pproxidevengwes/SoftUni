import java.sql.*;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Scanner;

public class SoftUni {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username default (root): ");
        String user = scanner.nextLine();
        user = user.equals("") ? "" : user;
        System.out.println();

        System.out.println("Enter password default (root): ");
        String pass = scanner.nextLine();
        pass = pass.equals("") ? "" : pass;
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user","root");
        props.setProperty("password","amara2113");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");

        String salary = scanner.nextLine();
        statement.setDouble(1, Double.parseDouble(salary));
        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            System.out.println(rs.getString("first_name") + " "+ rs.getString("last_name"));
        }
        connection.close();
    }
}
