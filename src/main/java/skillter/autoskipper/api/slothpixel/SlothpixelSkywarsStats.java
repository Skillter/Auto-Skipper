package skillter.autoskipper.api.slothpixel;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.*;
import skillter.autoskipper.api.hypixel.HypixelAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class SlothpixelSkywarsStats {

    public static HashMap<UUID, Float> getPlayersKD(ArrayList<UUID> uuid) throws InterruptedException, ExecutionException {
        HashMap<UUID, Float> playersKD = new HashMap<UUID, Float>();
        StringBuffer uuids = new StringBuffer();
        for (int i=0; i<uuid.size(); i++) {
            uuids.append(uuid.get(i));
            if (i < uuid.size()-1) uuids.append(",");
        }
        String uuidsString = uuids.toString().replaceAll("-", ""); // Slothpixel docs explain the uuid should not contain dashes
        String response = HttpRequest.get(SlothpixelAPI.API_URL + "players/" + uuidsString).accept("application/json").body();

        AtomicInteger counter = new AtomicInteger();
        JsonParser.parseString(response).getAsJsonArray().forEach((player) -> {
            Float playerKD = player.getAsJsonObject().getAsJsonObject("stats").getAsJsonObject("SkyWars").get("kill_death_ratio").getAsFloat();
            playersKD.put(uuid.get(counter.get()), playerKD);
            counter.getAndIncrement();
        });
        return playersKD;
    }

    public static HashMap<UUID, Integer> getPlayersLevel(ArrayList<UUID> uuid) throws ExecutionException, InterruptedException {
        HashMap<UUID, Integer> playersLevel = new HashMap<UUID, Integer>();
        StringBuffer uuids = new StringBuffer();
        for (int i=0; i<uuid.size(); i++) {
            uuids.append(uuid.get(i));
            if (i < uuid.size()-1) uuids.append(",");
        }
        String uuidsString = uuids.toString().replaceAll("-", ""); // Slothpixel docs explain the uuid should not contain dashes
        String response = HttpRequest.get(SlothpixelAPI.API_URL + "players/" + uuidsString).accept("application/json").body();

        AtomicInteger counter = new AtomicInteger();
        JsonParser.parseString(response).getAsJsonArray().forEach((player) -> {
            int experience = player.getAsJsonObject().getAsJsonObject("stats").getAsJsonObject("SkyWars").get("experience").getAsInt();
            playersLevel.put(uuid.get(counter.get()), experience);
            counter.getAndIncrement();
        });
        return playersLevel;
    }

}
