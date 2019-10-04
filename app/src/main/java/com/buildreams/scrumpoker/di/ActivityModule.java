package com.buildreams.scrumpoker.di;

import com.buildreams.scrumpoker.view.DeploymentCardActivity;
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
    @ClassKey(DeploymentCardActivity.class)
    abstract AndroidInjector.Factory<?>
    bindLoginActivityInjectorFactory(MainActivitySubcomponent.Factory factory);

    @Subcomponent(modules = {ViewModelModule.class, AdapterModule.class})
    public interface MainActivitySubcomponent extends AndroidInjector<DeploymentCardActivity> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<DeploymentCardActivity> {
        }
    }
}
