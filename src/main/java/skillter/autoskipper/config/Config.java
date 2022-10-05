package skillter.autoskipper.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import skillter.autoskipper.Reference;

import java.io.File;

public class Config {

    public static File configFile = new File(Loader.instance().getConfigDir(), Reference.MOD_ID + ".cfg");

    public static void save(String category, String key, int value) {
        Configuration config = new Configuration(configFile);
        config.load();
        config.getCategory(category).get(key).set(value);
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static int getInt(String category, String key) {
        Configuration config = new Configuration(configFile);
        config.load();
        return config.getCategory(category).get(key).getInt();
    }

    public static void save(String category, String key, String value) {
        Configuration config = new Configuration(configFile);
        config.load();
        config.getCategory(category).get(key).set(value);
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static String getString(String category, String key) {
        Configuration config = new Configuration(configFile);
        config.load();
        return config.getCategory(category).get(key).getString();
    }


    public static void save(String category, String key, boolean value) {
        Configuration config = new Configuration(configFile);
        config.load();
        config.getCategory(category).get(key).set(value);
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static boolean getBoolean(String category, String key) {
        Configuration config = new Configuration(configFile);
        config.load();
        return config.getCategory(category).get(key).getBoolean();
    }
}
