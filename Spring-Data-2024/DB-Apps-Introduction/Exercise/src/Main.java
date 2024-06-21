import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class Main {

    private static final DBTools DBTOOLS = new DBTools("root", "", "minions_db");

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws SQLException, IOException {
        exe9();
    }

    private static void exe9() throws SQLException, IOException {
        CallableStatement callableStatement = DBTOOLS.getConnection().prepareCall("CALL usp_get_older(?)");
        callableStatement.setInt(1, Integer.parseInt(READER.readLine()));
        ResultSet resultSet = callableStatement.executeQuery();
        resultSet.next();
        System.out.printf("%s, %s%n", resultSet.getString(1), resultSet.getInt(2));
    }

    private static void exe8() throws IOException, SQLException {
        int[] minionsId = Arrays.stream(READER.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Arrays.stream(minionsId).forEach(id -> {
            String sql = "UPDATE minions SET name = LOWER(name), age = age + 1 WHERE id IN(?)";

            try {
                PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        ResultSet resultSet = DBTOOLS.getConnection().createStatement().executeQuery("SELECT name, age FROM minions");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));
        }

    }

    private static void exe7() throws IOException, SQLException {
        ResultSet resultSet = DBTOOLS.getConnection().createStatement().executeQuery("SELECT name FROM minions");

        ArrayDeque<String> deque = new ArrayDeque<>();

        while (resultSet.next()) {
            deque.add(resultSet.getString(1));
        }
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
            if (!deque.isEmpty()) {
                System.out.println(deque.removeLast());
            }
        }
    }

    private static void exe6() throws IOException, SQLException {
        int villainId = Integer.parseInt(READER.readLine());
        String villainName = findVillainNameById(villainId);

        if (villainName.isEmpty()) {
            System.out.println("No such villain was found");
        } else {
            int releaseMinionsCount = releaseMinions(villainId);
            deleteVillain(villainId);
            System.out.printf("%s was deleted%n%d minions released", villainName, releaseMinionsCount);
        }
    }

    private static void deleteVillain(int villainId) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("DELETE FROM villains WHERE villain_id = ?");
        preparedStatement.setInt(1, villainId);
        preparedStatement.executeUpdate();
    }

    private static int releaseMinions(int villainId) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");
        preparedStatement.setInt(1, villainId);

        return preparedStatement.executeUpdate();
    }

    private static String findVillainNameById(int villainId) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("SELECT name FROM villains WHERE name = ?");
        preparedStatement.setInt(1, villainId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("name");
        }

        return "";
    }

    private static void exe5() throws SQLException, IOException {
        String country = READER.readLine();

        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("UPDATE towns SET name = UPPER(name) WHERE country = ?");
        preparedStatement.setString(1, country);
        int i = preparedStatement.executeUpdate();

        if (i == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.%n", i);
            preparedStatement = DBTOOLS.getConnection().prepareStatement("SELECT name FROM towns WHERE country = ?");
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> names = new ArrayList<>();

            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
            }
            System.out.printf("[%s]", String.join(", ", names));
        }
    }

    private static void exe4() throws SQLException, IOException {
        String[] minionsTokens = READER.readLine().split("\\s+");
        String minionName = minionsTokens[1];
        int minionAge = Integer.parseInt(minionsTokens[2]);
        String townName = minionsTokens[3];

        String villainName = READER.readLine().split("\\s+")[1];

        int townID = findTownIdByName(townName);
        if (townID == 0) {
            townID = createTown(townName);
            System.out.printf("Town %s was added to the database.%n", townName);
        }

        int minionId = createMinion(minionName, minionAge, townID);
        int villainID = findVillainByName(villainName);
        if (villainID == 0) {
            villainID = createVillain(villainName);
            System.out.printf("Villain %s was added to the database.%n", villainName);
        }

        populateMinionsVillains(minionId, villainID);
        System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);
    }

    private static void populateMinionsVillains(int minionId, int villainId) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("INSERT INTO minions_villains VALUE (?, ?)");
        preparedStatement.setInt(1, minionId);
        preparedStatement.setInt(2, villainId);
        preparedStatement.executeUpdate();
    }

    private static int createVillain(String villainName) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("INSERT INTO villains(name, evilness_factor) VALUE(?, 'evil')");
        preparedStatement.setString(1, villainName);
        preparedStatement.executeUpdate();

        preparedStatement = DBTOOLS.getConnection().prepareStatement("SELECT id FROM villains WHERE name = ?");
        preparedStatement.setString(1, villainName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    private static int findVillainByName(String villainName) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("SELECT id FROM villains WHERE name = ?");
        preparedStatement.setString(1, villainName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return 0;
    }

    private static int createMinion(String minionName, int minionAge, int townID) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("INSERT INTO minions(name, age, town_id) VALUE (?, ?, ?)");
        preparedStatement.setString(1, minionName);
        preparedStatement.setInt(2, minionAge);
        preparedStatement.setInt(3, townID);
        preparedStatement.executeUpdate();

        preparedStatement = DBTOOLS.getConnection().prepareStatement("SELECT id FROM minions WHERE name = ?");
        preparedStatement.setString(1, minionName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    private static int createTown(String townName) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("INSERT INTO towns(name) VALUE(?)");
        preparedStatement.setString(1, townName);
        preparedStatement.executeUpdate();

        preparedStatement = DBTOOLS.getConnection().prepareStatement("SELECT id FROM towns WHERE name = ?");
        preparedStatement.setString(1, townName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    private static Integer findTownIdByName(String townName) throws SQLException {
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement("SELECT id FROM towns WHERE name = ?");
        preparedStatement.setString(1, townName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }

        return 0;
    }


    private static void exe3() throws IOException, SQLException {
        int villainID = Integer.parseInt(READER.readLine());

        String sql = "SELECT name FROM villains WHERE id = ?";
        PreparedStatement preparedStatement = DBTOOLS.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, villainID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            System.out.println("No villain with ID 10 exists in the database.");

            return;
        }
        String villainName = resultSet.getString("name");
        System.out.println("Villain: " + villainName);

        sql = "SELECT m.name, m.age  FROM minions m " +
                "JOIN minions_villains mv ON m.id = mv.minion_id " +
                "WHERE villain_id = ?";
        preparedStatement = DBTOOLS.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, villainID);
        resultSet = preparedStatement.executeQuery();
        int index = 0;

        while (resultSet.next()) {
            System.out.printf("%d. %s %d", ++index, resultSet.getString("name"),
                    resultSet.getInt("age")).append(System.lineSeparator());
        }
    }

    public static void exe2() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        //Statement logic-> create query
        String sql = "SELECT v.name, COUNT(mv.minion_id) AS count FROM villains v " +
                "JOIN minions_villains mv ON v.id = mv.villain_id " +
                "GROUP BY v.name " +
                "HAVING count > 15 ORDER BY count DESC;";
        ResultSet resultSet = DBTOOLS.getConnection().createStatement().executeQuery(sql);

        //Output of query
        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString(1), resultSet.getInt(2)).append(System.lineSeparator());
        }
    }
}

