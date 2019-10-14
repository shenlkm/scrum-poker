package com.buildreams.scrumpoker.di;

import com.buildreams.scrumpoker.view.DashboardCardActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        ActivityModule.DeploymentCardActivitySubcomponent.class
})
public abstract class ActivityModule {

    @Binds
    @IntoMap
    @ClassKey(DashboardCardActivity.class)
    abstract AndroidInjector.Factory<?>
    bindDeploymentCardActivityInjectorFactory(DeploymentCardActivitySubcomponent.Factory factory);


    @Subcomponent(modules = {ViewModelModule.class, AdapterModule.class})
    public interface DeploymentCardActivitySubcomponent extends AndroidInjector<DashboardCardActivity> {
        @Subcomponent.Factory
        interface Factory extends AndroidInjector.Factory<DashboardCardActivity> {
        }
    }
}
