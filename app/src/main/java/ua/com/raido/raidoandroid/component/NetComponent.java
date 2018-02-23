package ua.com.raido.raidoandroid.component;


import javax.inject.Singleton;

import dagger.Component;
import ua.com.raido.raidoandroid.application.App;
import ua.com.raido.raidoandroid.module.AppModule;
import ua.com.raido.raidoandroid.module.NetModule;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(App app);
}
