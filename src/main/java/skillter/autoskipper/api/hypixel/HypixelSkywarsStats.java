package skillter.autoskipper.api.hypixel;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class HypixelSkywarsStats {

    public static HashMap<UUID, Float> getPlayersKD(ArrayList<UUID> uuid) throws InterruptedException, ExecutionException {
        HashMap<UUID, Float> playerKD = new HashMap<UUID, Float>();
        HypixelAPI.api.getPlayerByUuid(uuid.get(0)).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        }).thenAccept(x -> {
            JsonObject skywars = x.getPlayer().getObjectProperty("stats").getAsJsonObject("SkyWars");
            playerKD.put(x.getPlayer().getUuid(), (float) skywars.get("kills").getAsInt() / skywars.get("deaths").getAsInt());
        }).get();
       return playerKD;

    }

    public static ArrayList<Integer> getPlayersLevel(ArrayList<UUID> uuid) throws ExecutionException, InterruptedException {
        ArrayList<Integer> playerLevel = new ArrayList<Integer>();
        HypixelAPI.api.getPlayerByUuid(uuid.get(0)).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        }).thenAccept(x -> {
            JsonObject skywars = x.getPlayer().getObjectProperty("stats").getAsJsonObject("SkyWars");
            playerLevel.add(skywars.get("skywars_experience").getAsInt());
        }).get();
        return playerLevel;

    }
}
