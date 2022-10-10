package skillter.autoskipper.database;
import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.MySQLDataException;
import net.minecraftforge.fml.common.Loader;
import skillter.autoskipper.Reference;
import skillter.autoskipper.config.ConfigHandler;
import skillter.autoskipper.database.tables.SkywarsTable;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class Database {

    public static String databasePath = "jdbc:sqlite:" + new File(Loader.instance().getConfigDir(), "data.db").getAbsolutePath();

    static private Connection connection = null;
    // Connecting to a database automatically creates it if it doesn't already exist
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Calling the Driver for database so it loads
            Class.forName("org.sqlite.JDBC");
            if (connection != null && connection.isClosed() == false) return connection;
            else {
                connection = DriverManager.getConnection(databasePath);
                if (connection != null) {
                    DatabaseMetaData meta = connection.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    ConfigHandler.databaseReachable = true;
                    return connection;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                ConfigHandler.databaseReachable = false;
                connection.close();
                connection = null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        try (Connection connection = getConnection()){
            if (connection != null) {
                // Gson gson = new Gson();
                // String skywars = gson.toJson(new SkywarsTable("testUUID", "testPlayernickname", 2.1f, 13.0f));
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS Skywars (\n"
                            + "	uuid TEXT,\n"
                            + "	nick TEXT,\n"
                            + "	kd DECIMAL,\n"
                            + "	level DECIMAL\n"
                            + ");");
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commitTransaction(Statement statement) throws Exception {
        int count = 0;

        while (true) {
            try {
                    statement.executeUpdate("COMMIT TRANSACTION");
            }
            catch (Exception e) {
                if (e.getMessage().startsWith("[SQLITE_BUSY]") && count < 30) {
                    Thread.sleep(1000);
                    count++;

                    continue;
                }
                else {
                    e.printStackTrace();
                }
            }
            return;
        }
    }





    public static void performUpdate(Statement statement, long id, int action, int table) {
        try {
            int rolledBack = 1;
            if (action == 1) {
                rolledBack = 0;
            }

            if (table == 1) {
                statement.executeUpdate("UPDATE container SET rolled_back='" + rolledBack + "' WHERE rowid='" + id + "'");
            }
            else {
                statement.executeUpdate("UPDATE block SET rolled_back='" + rolledBack + "' WHERE rowid='" + id + "'");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
