package gift.makemoney.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by malk on 16/9/13.
 */

public class RestApi {

    public static final String CLIENT_ID = "b0cbd23d5d1b188ffbf313d0c78071280f3d506d0279a1d31302ad87548b1beb";
    public static final String BASE_URL = "https://api.unsplash.com/";

    private static ApiService mApiService;

    public static ApiService getApiService() {
        if (mApiService == null) {
            mApiService = buildRetrofit().create(ApiService.class);
        }
        return mApiService;
    }

    private static Retrofit buildRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new StethoInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)//超时时间
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz == Field.class || clazz == Method.class;
            }
        };

        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(exclusionStrategy)
                .addDeserializationExclusionStrategy(exclusionStrategy)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ApiErrorGsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
        return retrofit;
    }

}
