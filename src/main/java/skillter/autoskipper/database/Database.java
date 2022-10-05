package skillter.autoskipper.database;
import com.google.gson.Gson;
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

    public static Connection connection = null;

    // Connecting to a database automatically creates it if it doesn't already exist
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(databasePath);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                ConfigHandler.databaseReachable = true;
                return connection;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        if (ConfigHandler.databaseReachable == false || connection == null) {
            getConnection();
        }
        if (ConfigHandler.databaseReachable && connection != null) {
            Gson gson = new Gson();
            String skywars = gson.toJson(new SkywarsTable("testUUID", "testPlayernickname", 2.1f, 13.0f));
            for (int i=0; i<1; i++) {
                String tableName = skywars;
                String statement = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                        + "	id integer PRIMARY KEY,\n"
                        + "	name text NOT NULL,\n"
                        + "	capacity real\n"
                        + ");";
            }
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
                statement.executeUpdate("UPDATE " + ConfigHandler.prefix + "container SET rolled_back='" + rolledBack + "' WHERE rowid='" + id + "'");
            }
            else {
                statement.executeUpdate("UPDATE " + ConfigHandler.prefix + "block SET rolled_back='" + rolledBack + "' WHERE rowid='" + id + "'");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
