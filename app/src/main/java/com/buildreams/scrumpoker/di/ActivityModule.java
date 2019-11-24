package com.buildreams.scrumpoker.di;

import com.buildreams.scrumpoker.view.DashboardCardActivity;
import com.buildreams.scrumpoker.view.DeckSettingActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        ActivityModule.DeploymentCardActivitySubcomponent.class,
        ActivityModule.DeckSettingActivitySubcomponent.class
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

    @Binds
    @IntoMap
    @ClassKey(DeckSettingActivity.class)
    abstract AndroidInjector.Factory<?>
    bindDeckSettingInjectorFactory(DeckSettingActivitySubcomponent.Factory factory);


    @Subcomponent(modules = {ViewModelModule.class})
    public interface DeckSettingActivitySubcomponent extends AndroidInjector<DeckSettingActivity> {
        @Subcomponent.Factory
        interface Factory extends AndroidInjector.Factory<DeckSettingActivity> {
        }
    }
}
