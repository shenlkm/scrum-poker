package com.buildreams.scrumpoker.di;

import com.buildreams.scrumpoker.view.fragment.AboutFragment;
import com.buildreams.scrumpoker.view.fragment.DashboardCardFragment;
import com.buildreams.scrumpoker.view.fragment.SelectedCardFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        FragmentModule.DeploymentCardFragmentSubcomponent.class,
        FragmentModule.SelectedCardFragmentSubcomponent.class,
        FragmentModule.AboutFragmentFragmentSubcomponent.class
})
public abstract class FragmentModule {

    @Binds
    @IntoMap
    @ClassKey(DashboardCardFragment.class)
    abstract AndroidInjector.Factory<?>
    bindDeploymentCardFragmentInjectorFactory(DeploymentCardFragmentSubcomponent.Factory factory);


    @Subcomponent(modules = {ViewModelModule.class})
    public interface DeploymentCardFragmentSubcomponent extends AndroidInjector<DashboardCardFragment> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<DashboardCardFragment> {
        }
    }

    @Binds
    @IntoMap
    @ClassKey(SelectedCardFragment.class)
    abstract AndroidInjector.Factory<?>
    bindSelectedCardFragmentInjectorFactory(SelectedCardFragmentSubcomponent.Factory factory);

    @Subcomponent(modules = {ViewModelModule.class})
    public interface SelectedCardFragmentSubcomponent extends AndroidInjector<SelectedCardFragment> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<SelectedCardFragment> {
        }
    }

    @Binds
    @IntoMap
    @ClassKey(AboutFragment.class)
    abstract AndroidInjector.Factory<?>
    bindAboutFragmentInjectorFactory(AboutFragmentFragmentSubcomponent.Factory factory);

    @Subcomponent(modules = {ViewModelModule.class})
    public interface AboutFragmentFragmentSubcomponent extends AndroidInjector<AboutFragment> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<AboutFragment> {
        }
    }
}
