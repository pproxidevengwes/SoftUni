import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _09_IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        Scanner scanner = new Scanner(System.in);
        int minionId = Integer.parseInt(scanner.nextLine());

        PreparedStatement procedure = connection.prepareStatement(
                "CALL usp_get_older(?);");
        procedure.setInt(1, minionId);
        procedure.executeQuery();

        PreparedStatement info = connection.prepareStatement(
                "SELECT name, age FROM minions");

        ResultSet resultSet = info.executeQuery();
        
        while (resultSet.next()) {
            String minion = resultSet.getString("name");
            int minionAge = resultSet.getInt("age");
            System.out.println(minion + " " + minionAge);
        }

        connection.close();
    }
}

