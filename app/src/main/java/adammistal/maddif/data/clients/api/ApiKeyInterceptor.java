package adammistal.maddif.data.clients.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Adam on 23.05.2017.
 */

public class ApiKeyInterceptor implements Interceptor {

    private String api_key;

    public ApiKeyInterceptor(String api_key){
        this.api_key = api_key;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", api_key)
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
