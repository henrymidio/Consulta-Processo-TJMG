package org.cptjmg.consultaprocesso;

import android.app.Application;

import org.cptjmg.consultaprocesso.di.AppComponent;
import org.cptjmg.consultaprocesso.di.AppModule;
import org.cptjmg.consultaprocesso.di.DaggerAppComponent;

public class MainApp extends Application {
    private AppComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        myComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();

        //myComponent.inject(this);
    }

}
