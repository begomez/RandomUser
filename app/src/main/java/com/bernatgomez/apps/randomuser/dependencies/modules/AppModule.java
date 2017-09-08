package com.bernatgomez.apps.randomuser.dependencies.modules;


import com.bernatgomez.apps.randomuser.BuildConfig;
import com.bernatgomez.apps.randomuser.sources.IDataSource;
import com.bernatgomez.apps.randomuser.sources.RestDataSource;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by bernatgomez on 07/09/2017.
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    public RestDataSource provideDataSource(Bus bus, Retrofit gateway) {
        return new RestDataSource(bus, gateway);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient client) {
        final String BASE_URL = "https://randomuser.me/api/";

        Retrofit gateway =
            new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                    .build();

        return gateway;
    }

    @Singleton
    @Provides
    public OkHttpClient provideHTTPClient() {
        final int POOL_SIZE = 1;
        final int TIMEOUT = 30 * 1000;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

            //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //builder.addInterceptor(interceptor);
        }

        builder.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        builder.connectionPool(new ConnectionPool(POOL_SIZE, TIMEOUT, TimeUnit.MILLISECONDS));
        builder.retryOnConnectionFailure(true);

        return builder.build();
    }

    @Singleton
    @Provides
    public Bus provideBus() {
        return new Bus(ThreadEnforcer.MAIN);
    }
}
