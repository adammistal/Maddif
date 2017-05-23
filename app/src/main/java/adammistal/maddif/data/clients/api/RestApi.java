package adammistal.maddif.data.clients.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RestApi {
    @GET("/v1/gifs/trending")
    Observable<List<GifItem>> getTrendingGifs(@Query("limit") int limit);

}
