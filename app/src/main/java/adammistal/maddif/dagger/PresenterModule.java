package adammistal.maddif.dagger;

import adammistal.maddif.presentation.home.HomeContract;
import adammistal.maddif.presentation.home.HomePresenter;
import dagger.Module;
import dagger.Provides;


@Module
public class PresenterModule {


 @Provides
    public HomeContract.Presenter provideHomePresenter(HomePresenter presenter) {
        return presenter;
    }
}
