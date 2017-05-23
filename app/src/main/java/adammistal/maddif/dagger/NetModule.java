package adammistal.maddif.dagger;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import adammistal.maddif.data.clients.api.ApiKeyInterceptor;
import adammistal.maddif.data.clients.api.FeedDeserialize;
import adammistal.maddif.data.clients.api.GifItem;
import adammistal.maddif.data.clients.api.RestApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.schedulers.Schedulers;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private String mBaseUrl;
    private String apiKey;

    public NetModule(String baseUrl,String apiKey) {
        this.mBaseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    FeedDeserialize provideFeedDeserialize() {
        return new FeedDeserialize();
    }
    @Provides
    @Singleton
    Gson provideGson(FeedDeserialize feedDeserialize) {
        Type classType = new TypeToken<List<GifItem>>() {}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(classType,feedDeserialize);
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    public ApiKeyInterceptor provideApiKeyInterceptor(){
        return new ApiKeyInterceptor(apiKey);
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, ApiKeyInterceptor apiKeyInterceptor) {

        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(apiKeyInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapter)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public RestApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }


}
