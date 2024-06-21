import java.sql.*;
import java.util.Properties;

public class _02_MinionsCount {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT name, count(distinct mv.minion_id) as minion_count " +
                        "FROM villains as v " +
                        "JOIN minions_villains as mv ON mv.villain_id = v.id " +
                        "GROUP BY mv.villain_id " +
                        "HAVING minion_count > 15 " +
                        "ORDER BY minion_count DESC;");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString("name");
            int minionCount = resultSet.getInt("minion_count");
            System.out.println(villainName + " " + minionCount);
        }

        connection.close();

    }
}
