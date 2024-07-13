import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _08_IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        System.out.print("Enter minion(s) id: ");

        Scanner scanner = new Scanner(System.in);
        String[] minionId = scanner.nextLine().split("\\s+");

        for (int i = 0; i < minionId.length; i++) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE minions SET name=lower(name),age=age+1 WHERE id = ?;");
            statement.setInt(1, Integer.parseInt(minionId[i]));
            statement.executeUpdate();
        }

        PreparedStatement statement = connection.prepareStatement(
                "SELECT name, age FROM minions");

        ResultSet resultSet = statement.executeQuery();
      
        while (resultSet.next()) {
            String minion = resultSet.getString("name");
            int minionAge = resultSet.getInt("age");
            System.out.println(minion + " " + minionAge);
        }

        connection.close();
    }
}
