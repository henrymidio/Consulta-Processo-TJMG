package org.cptjmg.consultaprocesso.di;

import org.cptjmg.consultaprocesso.MainApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainApp Application);
}
