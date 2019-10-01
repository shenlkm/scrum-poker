package com.buildreams.scrumpoker.di;

import com.buildreams.scrumpoker.view.MainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        ActivityModule.MainActivitySubcomponent.class,
})
public abstract class ActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity.class)
    abstract AndroidInjector.Factory<?>
    bindLoginActivityInjectorFactory(MainActivitySubcomponent.Factory factory);

    @Subcomponent(modules = {ViewModelModule.class})
    public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<MainActivity> {
        }
    }
}
