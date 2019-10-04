package com.buildreams.scrumpoker.di;

import com.buildreams.scrumpoker.view.adapter.DeploymentCardAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @Provides
    DeploymentCardAdapter providesCardAdapter() {
        return new DeploymentCardAdapter();
    }
}
