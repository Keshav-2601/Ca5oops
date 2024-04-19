import com.google.gson.Gson;
import java.util.List;

public class JsonConverter {
    // Convert List of Entities to a JSON String
    public static <Player> String playersListToJson(List<Player> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Convert a single Entity by Key as a JSON String
    public static <Player> String playerToJson(Player p) {
        Gson gson = new Gson();
        return gson.toJson(p);
    }
}
