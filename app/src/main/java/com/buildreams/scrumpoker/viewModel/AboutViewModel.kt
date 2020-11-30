package com.buildreams.scrumpoker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buildreams.scrumpoker.domain.entity.About
import javax.inject.Inject


class AboutViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val YEAR = 2019
        const val DEV_CAMILO = "Camilo A. Rodriguez"
        const val DEV_MAURICIO = "Mauricio Marin"
    }

    private val _about = MutableLiveData<About>()
    val about: LiveData<About> = _about

    fun buildAbout() {
        _about.value = About(
            "$YEAR - $DEV_CAMILO, $DEV_MAURICIO",
            "1.0.0",
            listOf(
                "<a href=\"https://github.com/camroga\">camroga</a>",
                "<a href=\"https://github.com/shenlkm\">shenlkm</a>"
            ),
            "https://github.com/shenlkm/scrum-poker",
            listOf()
        )
    }
}