import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

//      Connect to DB
        Properties credentials = new Properties();
        credentials.setProperty("user", username);
        credentials.setProperty("password", password);
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", credentials);

//      Execute Query
        PreparedStatement updateStatement =
                connection.prepareStatement("UPDATE employees SET first_name = ? WHERE employee_id = ?");
        connection.setAutoCommit(false);
        updateStatement.setString(1, "Changed");
        updateStatement.setLong(2, 3);

        int updateResult = updateStatement.executeUpdate();

        System.out.println(updateResult);

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM employees WHERE salary > ? LIMIT 10");
        preparedStatement.setDouble(1, 67000.0);
        ResultSet resultSet = preparedStatement.executeQuery();

//      Print result
        while (resultSet.next()) {
            long id = resultSet.getLong("employee_id");
            String first_name = resultSet.getString("first_name");
            double salary = resultSet.getDouble("salary");
            Timestamp hireDate = resultSet.getTimestamp("hire_date");

            System.out.printf("%d -%s %.2f %s%n", id, first_name, salary, hireDate);
        }
        connection.close();
    }
}
