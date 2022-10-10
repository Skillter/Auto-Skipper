package skillter.autoskipper.database.tables;

import skillter.autoskipper.database.Database;

import java.sql.*;

public class SkywarsTable {

    public static void insert(String uuid, String nick, float kd, float level) {
        try(Connection connection = Database.getConnection();
        Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO Skywars VALUES ('" + uuid + "', '" + nick + "', " + kd + ", " + level + ")";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Player get(String uuid) {
        String sqlQuery = "SELECT * FROM Skywars WHERE uuid = ?";
        ResultSet rs = null;
        try(Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);)
        {
            if (preparedStatement == null) return null;
            preparedStatement.setString(1, uuid);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Player player = new Player(
                        rs.getString("uuid"),
                        rs.getString("nick"),
                        rs.getFloat("kd"),
                        rs.getFloat("level")
                );
                return player;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return null;
    }

    public static class Player {
        String uuid, nick;
        float kd, level;
        public Player(String uuid, String nick, float kd, float level) {
            this.uuid = uuid;
            this.nick = nick;
            this.kd = kd;
            this.level = level;
        }
        @Override
        public String toString() {
            return uuid + ", " + nick + ", " + kd + ", " + level;
        }
    }



}
