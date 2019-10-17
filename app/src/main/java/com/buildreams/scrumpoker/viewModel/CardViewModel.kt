package com.buildreams.scrumpoker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buildreams.scrumpoker.domain.entity.Card
import javax.inject.Inject

class CardViewModel @Inject
constructor() : ViewModel() {

    lateinit var card: MutableLiveData<Card>

    init {
        card = MutableLiveData()
    }
}