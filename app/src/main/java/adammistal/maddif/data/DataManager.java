package adammistal.maddif.data;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import adammistal.maddif.data.clients.api.ApiResponseMapper;
import adammistal.maddif.data.clients.api.GifItem;
import adammistal.maddif.data.clients.api.RestApi;
import adammistal.maddif.domain.repositories.GifsRepository;


public class DataManager implements GifsRepository {

    private final RestApi restApi;
    private final ApiResponseMapper apiMapper;
    private final int MAX_GIGS_COUNT = 20;
    private String apiKey;

    public DataManager(RestApi restApi,String apiKey, ApiResponseMapper apiMapper) {
        this.restApi = restApi;
        this.apiMapper = apiMapper;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<List<String>> getTrendingGifs() {
        return getGifsFromWeb();
    }

    private Observable<List<String>> getGifsFromWeb() {
        return restApi.getTrendingGifs(apiKey,MAX_GIGS_COUNT).flatMap(new Function<List<GifItem>, ObservableSource<List<String>>>() {
            @Override
            public ObservableSource<List<String>> apply(@NonNull List<GifItem> responseBodies) throws Exception {
                return Observable.just(apiMapper.mapResponse(responseBodies));
            }
        });
    }

}
