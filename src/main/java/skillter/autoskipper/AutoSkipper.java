package skillter.autoskipper;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.Sys;
import skillter.autoskipper.api.hypixel.HypixelSkywarsStats;
import skillter.autoskipper.api.slothpixel.SlothpixelAPI;
import skillter.autoskipper.api.slothpixel.SlothpixelSkywarsStats;
import skillter.autoskipper.config.Config;
import skillter.autoskipper.database.Database;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
    public void init(FMLInitializationEvent e) throws InterruptedException, ExecutionException, MalformedURLException {
        Database.createTables();
        //SkywarsTable.insert("AAAAAathis is a UUID", "Mateusz2xm", 1.5f, 160.3f);
        //SkywarsTable.Player skywarsPlayer = SkywarsTable.get("AAAAAathis is a UUID");

        ArrayList<UUID> uuid = new ArrayList<>();
        uuid.add(UUID.fromString("12146e22-c10a-4219-8103-15502dc80243"));
        uuid.add(UUID.fromString("12146e22-c10a-4219-8103-15502dc80243"));
        //System.out.println("the player's kd is " + HypixelSkywarsStats.getPlayersLevel(uuid).toString());
        System.out.println("uwu " + SlothpixelSkywarsStats.getPlayersKD(uuid));

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