package skillter.autoskipper.database.tables;

import skillter.autoskipper.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        try(Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT uuid, nick, kd, level FROM Skywars");
        ) {
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
