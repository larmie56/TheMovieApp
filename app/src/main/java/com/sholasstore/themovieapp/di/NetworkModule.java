package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.BuildConfig;
import com.sholasstore.themovieapp.MovieService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public static MovieService providesApi(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    static Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    static OkHttpClient providesOkHttpClient(
            @LoggingInterceptor Interceptor loggingInterceptor,
            @RequestInterceptor Interceptor requestInterceptor
            ) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build();
    }

    @Provides
    @LoggingInterceptor
    static Interceptor providesLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @RequestInterceptor
    static Interceptor providesRequestInterceptor() {
        return new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();

                HttpUrl newUrl = originalUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .addQueryParameter("language", BuildConfig.LANGUAGE)
                        .build();
                Request.Builder newRequestBuilder = new Request.Builder().url(newUrl);
                final Request newRequest = newRequestBuilder.build();

                return chain.proceed(newRequest);
            }
        };
    }

}