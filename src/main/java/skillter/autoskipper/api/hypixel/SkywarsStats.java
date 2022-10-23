package skillter.autoskipper.api.hypixel;

import com.google.gson.JsonObject;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import skillter.autoskipper.config.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SkywarsStats {


    public static CompletableFuture<HashMap<UUID, Float>> getPlayersKD(ArrayList<UUID> uuid) throws InterruptedException {
        HashMap<UUID, Float> playerKD = new HashMap<UUID, Float>();
        HypixelAPI.api.getPlayerByUuid(uuid.get(0)).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        }).thenAccept(x -> {
            JsonObject skywars = x.getPlayer().getObjectProperty("stats").getAsJsonObject("SkyWars");
            playerKD.put(x.getPlayer().getUuid(), (float) skywars.get("kills").getAsInt() / skywars.get("deaths").getAsInt());

        }).complete();
        return CompletableFuture.completedFuture(playerKD);

    }

    public static HashMap<UUID, Float> getPlayersLevel() {
        return null;
    }
}
