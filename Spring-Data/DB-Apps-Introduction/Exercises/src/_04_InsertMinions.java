import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _04_InsertMinions {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        Scanner scanner = new Scanner(System.in);
        String[] minionInfo = scanner.nextLine().split("\\s+");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];
        String villainName = scanner.nextLine().split("\\s+")[1];

        int townId = getOrInsertTown(connection, minionTown);
        int villainID = getOrInsertVillain(connection, villainName);

        PreparedStatement insertMinions = connection.prepareStatement(
                "INSERT INTO minions(name,age,town_id) VALUES (?, ?, ?)");
        insertMinions.setString(1,minionName);
        insertMinions.setInt(2,minionAge);
        insertMinions.setInt(3,townId);
        insertMinions.executeUpdate();

        PreparedStatement lastMinion = connection.prepareStatement(
                "SELECT id FROM minions ORDER BY id desc");
                
        ResultSet lastMinionSet = lastMinion.executeQuery();
        lastMinionSet.next();
        int lastMinionId = lastMinionSet.getInt("id");

        PreparedStatement insertVillain = connection.prepareStatement(
                "INSERT INTO minions_villains VALUES (?, ?);");
        insertMinions.setString(1,minionName);
        insertMinions.setInt(2,minionAge);
        insertMinions.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
        
        connection.close();
    }

    private static int getOrInsertVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement selectVillain = connection.prepareStatement(
                "SELECT id FROM villains WHERE name = ? ");
        selectVillain.setString(1, villainName);

        ResultSet villainSet = selectVillain.executeQuery();
        int villainId = 0;

        if (!villainSet.next()) {
            PreparedStatement newVillain = connection.prepareStatement(
                    "INSERT INTO villains(name, evilness_factor) VALUES (?, ?);");
            newVillain.setString(1, villainName);
            newVillain.setString(2, "evil");
            newVillain.executeUpdate();

            ResultSet newVillainSet = selectVillain.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.%n", villainName);
        } else {
            villainId = villainSet.getInt("id");
        }
        return villainId;
    }

    private static int getOrInsertTown(Connection connection, String minionTown) throws SQLException {
        PreparedStatement selectTown = connection.prepareStatement(
                "SELECT id FROM towns WHERE name = ? ");
        selectTown.setString(1, minionTown);

        ResultSet townSet = selectTown.executeQuery();
        int townId = 0;

        if (!townSet.next()) {
            PreparedStatement newTown = connection.prepareStatement(
                    "INSERT INTO towns(name) VALUES (?);");
            newTown.setString(1, minionTown);
            newTown.executeUpdate();

            ResultSet newTownSet = selectTown.executeQuery();
            newTownSet.next();
            townId = newTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            townId = townSet.getInt("id");
        }
        return townId;
    }
}
