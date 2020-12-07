package com.buildreams.scrumpoker

import android.app.Application
import com.buildreams.scrumpoker.di.component.DaggerApplicationComponent

class ScrumPokerApplication : Application() {

    val appComponent = DaggerApplicationComponent.create()

}