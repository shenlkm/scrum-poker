package com.buildreams.scrumpoker;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import com.buildreams.scrumpoker.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ScrumPokerApplication extends Application implements HasActivityInjector,
        HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().create().inject(this);
    }

    // Dependency Injection
    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
