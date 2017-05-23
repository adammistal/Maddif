package adammistal.maddif.dagger;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final Context app;

    public ApplicationModule(Context application) {
        app = application;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return app;
    }

    @Provides
    @Singleton
    public Resources provideResources() {
        return app.getResources();
    }

    @Provides
    @Singleton
    public AssetManager provideAssets() {
        return app.getAssets();
    }


}
