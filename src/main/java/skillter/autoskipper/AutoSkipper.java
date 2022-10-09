package skillter.autoskipper;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import skillter.autoskipper.config.Config;
import skillter.autoskipper.database.Database;
import skillter.autoskipper.database.tables.SkywarsTable;

import javax.xml.crypto.Data;

@Mod(clientSideOnly = true, modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT_VERSIONS)
public class AutoSkipper {

    public static String prefix = EnumChatFormatting.GOLD + "[AutoSkipper] " + EnumChatFormatting.RESET;

    @Mod.Instance("autoskipper")
    public static AutoSkipper instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        configInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); // Calling the Driver for database so it loads
        Class.forName("org.sqlite.JDBC");
        Database.getConnection();
        Database.createTables();
        //SkywarsTable.insert("this is a UUID", "Mateusz1xm", 1.5f, 160.3f);
        SkywarsTable.Player skywarsPlayer = SkywarsTable.get("this is a UUID");
        System.out.println("test" + skywarsPlayer.toString());

    }


    private static void configInit() {
        if (!Config.configFile.exists()) {
            Configuration config = new Configuration(Config.configFile);
            config.load();
            config.get("general", "enabled", true);
            config.get("general", "api_key", "<API_KEY>");
            config.get("general", "better_than_me", true);
            config.get("general", "above", 12);
            if(config.hasChanged()){
                config.save();
            }
        }
    }
}