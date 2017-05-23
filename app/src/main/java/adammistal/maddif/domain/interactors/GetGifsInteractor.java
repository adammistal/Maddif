package adammistal.maddif.domain.interactors;


import java.util.List;

import javax.inject.Inject;

import adammistal.maddif.data.DataManager;
import adammistal.maddif.domain.repositories.GifsRepository;
import io.reactivex.Observable;
import io.reactivex.Scheduler;



public class GetGifsInteractor extends BaseUseCase<List<String>> {

    private GifsRepository repository;

    //region CONSTRUCTOR
    @Inject
    public GetGifsInteractor(Scheduler workScheduler, Scheduler mainSchduler, DataManager repository) {
        super(workScheduler, mainSchduler);
        this.repository = repository;

    }
    //endregion

    //region BIND OBSERVABLE
    @Override
    protected Observable<List<String>> createObservable() {
        //TODO Invoke method from Repository
        return repository.getTrendingGifs();
    }
    //endregion

}
