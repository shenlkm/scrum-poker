package com.buildreams.scrumpoker.di.component

import com.buildreams.scrumpoker.view.DashboardCardActivity
import com.buildreams.scrumpoker.view.DeckSettingActivity
import com.buildreams.scrumpoker.view.fragment.AboutFragment
import com.buildreams.scrumpoker.view.fragment.DashboardCardFragment
import com.buildreams.scrumpoker.view.fragment.SelectedCardFragment
import dagger.Component

@Component
interface ApplicationComponent {
    fun inject(activity: DashboardCardActivity)
    fun inject(activity: DeckSettingActivity)
    fun inject(fragment: AboutFragment)
    fun inject(fragment: DashboardCardFragment)
    fun inject(fragment: SelectedCardFragment)
}