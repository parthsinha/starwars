package com.starwars.services;

import com.starwars.BuildConfig;
import com.starwars.services.converter.StarWarsGsonConverter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class Services {

    private static final Long READ_TIMEOUT_MILLISECONDS = 60000L;
    private static final Long WRITE_TIMEOUT_MILLISECONDS = 60000L;
    private static final Long CONNECT_TIMEOUT_MILLISECONDS = 60000L;
    private static final boolean DO_LOGGING = true;
    private static Services ourInstance;
    private Retrofit secureRestAdapter;
    private ServiceEndpoints endpoints;

    private Services() {
        endpoints = new ServiceEndpoints();
    }

    public static Services getInstance() {
        if (ourInstance == null) {
            ourInstance = new Services();
        }

        if (ourInstance.endpoints == null) {
            ourInstance.endpoints = new ServiceEndpoints();
        }

        return ourInstance;
    }

    public static ServiceEndpoints getEndpoints() {
        return getInstance().endpoints;
    }

    private String getApiEndpoint() {
        return BuildConfig.SERVICE_END_POINT;
    }

    public Retrofit getSecureRestAdapter() {
        resetConnectionAdapter();
        return secureRestAdapter;
    }

    private OkHttpClient createSecureClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(CONNECT_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json");

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        return httpClient.build();
    }

    private void createSecureRestAdapter() {
        OkHttpClient okHttpClient = createSecureClient();

        Retrofit.Builder retrofitSecureBuilder = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getApiEndpoint());
        retrofitSecureBuilder.addConverterFactory(StarWarsGsonConverter.getInstance().getGsonConverter());
        secureRestAdapter = retrofitSecureBuilder.build();
    }

    private void resetConnectionAdapter() {
        ourInstance = null;
        secureRestAdapter = null;
        createSecureRestAdapter();
    }
}
