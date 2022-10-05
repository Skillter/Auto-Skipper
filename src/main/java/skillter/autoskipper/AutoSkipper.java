package skillter.autoskipper;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import skillter.autoskipper.config.Config;

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
    public void init(FMLInitializationEvent e) {

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