package adammistal.maddif.data.clients.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class FeedDeserialize implements JsonDeserializer<List<GifItem>> {

    @Override
    public List<GifItem> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<GifItem> items = new ArrayList<>();
        JsonObject obj = json.getAsJsonObject();
        JsonArray array = obj.getAsJsonArray("data");
        for(JsonElement item : array){
            JsonObject it = item.getAsJsonObject();
            JsonObject imgs = it.getAsJsonObject("images");
            GifItem gif = new GifItem();

            JsonObject wO = imgs.get("fixed_width").getAsJsonObject();
            String url = wO.get("url").getAsString();
            gif.setUrl(url);
            items.add(gif);
        }
        return items;
    }
}
