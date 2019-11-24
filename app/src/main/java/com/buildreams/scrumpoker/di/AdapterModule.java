package com.buildreams.scrumpoker.di;

import com.buildreams.scrumpoker.view.adapter.DashboardCardAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @Provides
    DashboardCardAdapter providesCardAdapter() {
        return new DashboardCardAdapter();
    }
}
