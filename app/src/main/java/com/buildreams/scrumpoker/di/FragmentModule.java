package com.buildreams.scrumpoker.di;

import com.buildreams.scrumpoker.view.fragment.DeploymentCardFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        FragmentModule.DeploymentCardFragmentSubcomponent.class})
public abstract class FragmentModule {

    @Binds
    @IntoMap
    @ClassKey(DeploymentCardFragment.class)
    abstract AndroidInjector.Factory<?>
    bindDeploymentCardFragmentInjectorFactory(DeploymentCardFragmentSubcomponent.Factory factory);


    @Subcomponent(modules = {ViewModelModule.class})
    public interface DeploymentCardFragmentSubcomponent extends AndroidInjector<DeploymentCardFragment> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<DeploymentCardFragment> {
        }
    }
}
