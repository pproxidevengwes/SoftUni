import java.sql.*;
import java.util.Scanner;

public class Diablo {
    public static void main(String[] args) throws SQLException {
        //Connect to DB
        Connection connection = DriverManager.getConnection("jdbc://localhost::3306/diablo", "root", "exGTfpgCU-57");

        //Execute Query
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        //Execute Query
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.id, u.first_name, u.last_name, ug.game_id FROM users AS u " +
                "JOIN users_games AS ug ON ug.user_id = u.id " +
                "WHERE u.user_name = ?");

        preparedStatement.setString(1, username);
        ResultSet result = preparedStatement.executeQuery();

        //Print Result
        Integer userId = result.getInt(1);
        if (result.next()) {
            System.out.printf("User: %s%n%s %s has played %d games",
                    username, result.getString(2), result.getString(3), result.getInt(4));

        } else {
            System.out.println();
        }
    }
}
