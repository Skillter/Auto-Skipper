package skillter.autoskipper.api.slothpixel;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.JsonObject;
import skillter.autoskipper.api.hypixel.HypixelAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class SlothpixelSkywarsStats {

    public static HashMap<UUID, Float> getPlayersKD(ArrayList<UUID> uuid) throws InterruptedException, ExecutionException {
        HashMap<UUID, Float> playerKD = new HashMap<UUID, Float>();
        StringBuffer uuids = new StringBuffer();
        for (int i=0; i<uuid.size(); i++) {
            uuids.append(uuid.get(i));
            if (i < uuid.size()-1) uuids.append(",");
        }
        uuids.toString().replaceAll("-", "");
        String response = HttpRequest.get(SlothpixelAPI.API_URL + "players/" + uuids.toString()).accept("application/json").body();

        System.out.println(response);
        //JsonObject skywars = x.getPlayer().getObjectProperty("stats").getAsJsonObject("SkyWars");
        //playerKD.put(x.getPlayer().getUuid(), (float) skywars.get("kills").getAsInt() / skywars.get("deaths").getAsInt());

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
