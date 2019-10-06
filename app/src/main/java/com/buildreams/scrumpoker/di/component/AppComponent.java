package com.buildreams.scrumpoker.di.component;

import com.buildreams.scrumpoker.ScrumPokerApplication;
import com.buildreams.scrumpoker.di.ActivityModule;
import com.buildreams.scrumpoker.di.FragmentModule;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

import javax.inject.Singleton;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                ActivityModule.class,
                FragmentModule.class
        })
public interface AppComponent extends AndroidInjector<ScrumPokerApplication> {

    @Override
    void inject(ScrumPokerApplication scrumPokerApplication);

    @Component.Builder
    interface Builder {
        AppComponent create();
    }
}
