package adammistal.maddif.data.clients.api;

import java.util.ArrayList;
import java.util.List;


public final class ApiResponseMapper {

    public List<String> mapResponse(List<GifItem> countries) {
        List<String> items = new ArrayList<>();
        for (GifItem c : countries) {
            items.add(c.getUrl());
        }
        return items;
    }
}
