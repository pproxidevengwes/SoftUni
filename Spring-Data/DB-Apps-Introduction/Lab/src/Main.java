import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user","");
        props.setProperty("password","");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement statement = connection.prepareStatement("SELECT user_name, first_name, last_name, COUNT(ug.id) " +
                "FROM users AS u " +
                "JOIN users_games AS ug ON ug.user_id = u.id " +
                "WHERE user_name = ? " +
                "GROUP BY ug.user_id" );

        Scanner scanner = new Scanner(System.in);
        String username= scanner.nextLine();
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            resultSet.getMetaData();
            String dbUsername = resultSet.getString("user_name");
            String dbFirstName = resultSet.getString("first_name");
            String dbLastName = resultSet.getString("last_name");
            int dbGamesCount = resultSet.getInt("COUNT(ug.id)");

            System.out.printf("User: %s\n%s %s has played %d games \n", dbUsername, dbFirstName, dbLastName, dbGamesCount);
        } else {
            System.out.println("No such user exists");
        }
    }
}
