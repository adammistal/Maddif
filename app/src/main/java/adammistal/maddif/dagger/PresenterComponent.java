package adammistal.maddif.dagger;

import javax.inject.Singleton;

import adammistal.maddif.presentation.home.Home;
import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class, PresenterModule.class, UseCasesModule.class})
public interface PresenterComponent {
    void inject(Home home);


}
