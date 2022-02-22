import java.sql.*;
import java.util.ArrayDeque;
import java.util.Properties;

public class _07_PrintAllMinions {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "amara2113");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        ArrayDeque<String> minions = new ArrayDeque<>();

        PreparedStatement statement = connection.prepareStatement(
                "SELECT name FROM minions");

        ResultSet resultSet = statement.executeQuery();
      
        while (resultSet.next()) {
            minions.offer(resultSet.getString("name"));
        }
      
        while (!minions.isEmpty()) {
            System.out.println(minions.poll());
          
            if (!minions.isEmpty()) {
                System.out.println(minions.removeLast());
            }
        }
    }
}
