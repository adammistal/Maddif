package adammistal.maddif.dagger;

import javax.inject.Named;
import javax.inject.Singleton;

import adammistal.maddif.data.DataManager;
import adammistal.maddif.data.clients.api.ApiResponseMapper;
import adammistal.maddif.data.clients.api.RestApi;
import adammistal.maddif.domain.interactors.GetGifsInteractor;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class UseCasesModule {

    private String apiKey;

    public UseCasesModule(String apiKey){
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    @Named("main")
    public Scheduler provideMainSchedulerProvider() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named("work")
    public Scheduler provideWorkSchedulerProvider() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    public ApiResponseMapper provideApiMapper() {
        return new ApiResponseMapper();
    }

    @Provides
    @Singleton
    public DataManager provideCountriesRepository(RestApi restApi, ApiResponseMapper mapper) {
        return new DataManager(restApi,apiKey, mapper);
    }

    @Provides
    @Singleton
    public GetGifsInteractor provideGetCountriesInteractor(@Named("work") Scheduler workScheduler, @Named("main") Scheduler mainSchduler, DataManager countriesRepository) {
        return new GetGifsInteractor(workScheduler, mainSchduler, countriesRepository);
    }

}
