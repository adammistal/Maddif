package adammistal.maddif;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import adammistal.maddif.dagger.ApplicationModule;
import adammistal.maddif.dagger.DaggerPresenterComponent;
import adammistal.maddif.dagger.NetModule;
import adammistal.maddif.dagger.PresenterComponent;
import adammistal.maddif.dagger.PresenterModule;
import adammistal.maddif.dagger.UseCasesModule;


public class MyApp extends Application {
    //region VARIABLES
    private PresenterComponent presenterComponent;

    //endregion
    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //region DAGGER 2
        ApplicationModule am = new ApplicationModule(this);
        NetModule netModule = new NetModule(getString(R.string.base_url));

        presenterComponent = DaggerPresenterComponent.builder()
                .applicationModule(am)
                .useCasesModule(new UseCasesModule(getString(R.string.api_key)))
                .netModule(netModule)
                .presenterModule(new PresenterModule())
                .build();
        //endregion
    }

    public PresenterComponent getPresenterComponent() {
        return presenterComponent;
    }

}
