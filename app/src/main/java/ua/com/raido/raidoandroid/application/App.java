package ua.com.raido.raidoandroid.application;

import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ua.com.raido.raidoandroid.R;
import ua.com.raido.raidoandroid.component.DaggerNetComponent;
import ua.com.raido.raidoandroid.component.NetComponent;
import ua.com.raido.raidoandroid.module.AppModule;
import ua.com.raido.raidoandroid.module.NetModule;
import ua.com.raido.raidoandroid.service.RaidoService;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    private static final String TAG = "APP";

    private NetComponent mNetComponent;

    @Inject
    RaidoService raidoService;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        mNetComponent.inject(this);

        raidoService.getRasim()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rsm -> Log.e(TAG, rsm.getContent()),
                        throwable -> {},
                        () -> Log.e(TAG, "finished"));

        initCalligraphy();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
