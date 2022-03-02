import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_SelectByVillain {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainStatement = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?");
        villainStatement.setInt(1, villainId);
        ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
            return;
        }

        String villainName = villainSet.getString("name");
        System.out.println("Villain: " + villainName);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT m.name, m.age " +
                        "FROM minions as m " +
                        "JOIN minions_villains as mv ON mv.minion_id = m.id " +
                        "WHERE mv.villain_id = ?;");
        statement.setInt(1, villainId);

        ResultSet minionsSet = statement.executeQuery();

        for (int i = 1; minionsSet.next(); i++) {
            String name = minionsSet.getString("name");
            int age = minionsSet.getInt("age");
            System.out.printf("%d. %s %d%n", i, name, age);
        }

        connection.close();
    }
}
