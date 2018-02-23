package ua.com.raido.raidoandroid.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.com.raido.raidoandroid.service.RaidoService;

@Module
public class NetModule {
    @Provides
    @Singleton
    Retrofit retrofitProvider() {
        return new Retrofit.Builder()
                .baseUrl("https://raido.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    RaidoService raidoServiceProvider(Retrofit retrofit) {
        return retrofit.create(RaidoService.class);
    }
}
