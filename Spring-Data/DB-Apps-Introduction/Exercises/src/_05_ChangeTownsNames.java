import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_ChangeTownsNames {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        PreparedStatement updateTownNames = connection.prepareStatement(
                "UPDATE towns SET name = upper(name) WHERE country = ?;");
        updateTownNames.setString(1, country);
        int updatedCount = updateTownNames.executeUpdate();

        if (updatedCount == 0) {
            System.out.println("No town names were affected");
            return;
        }

        System.out.println(updatedCount + " town names were affected.");
        
        PreparedStatement selectAllTowns = connection.prepareStatement(
                "SELECT name FROM towns WHERE country = ?");
        selectAllTowns.setString(1, country);

        ResultSet resultSet = selectAllTowns.executeQuery();
        
        List<String> towns = new ArrayList<>();
        while (resultSet.next()) {
            String townName = resultSet.getString("name");
            towns.add(townName);
        }
        System.out.println(towns);

        connection.close();
    }
}
