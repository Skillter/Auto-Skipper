package skillter.autoskipper.api.hypixel;

import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import skillter.autoskipper.config.Config;

import java.util.UUID;

public class HypixelAPI {
    private static HypixelHttpClient client = new ApacheHttpClient(UUID.fromString(Config.getString("general","api_key")));
    public static net.hypixel.api.HypixelAPI api = new net.hypixel.api.HypixelAPI(client);

}
