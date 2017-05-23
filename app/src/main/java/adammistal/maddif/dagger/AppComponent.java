package adammistal.maddif.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Component;
import adammistal.maddif.dagger.ApplicationModule;

@Singleton
@Component(
        modules = ApplicationModule.class
)
public interface AppComponent {

}
