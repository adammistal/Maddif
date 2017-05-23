package adammistal.maddif.domain.repositories;


import java.util.List;

import io.reactivex.Observable;


public interface GifsRepository {

    Observable<List<String>> getTrendingGifs();

}
