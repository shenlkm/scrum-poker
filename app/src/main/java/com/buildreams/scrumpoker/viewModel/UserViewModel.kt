package com.buildreams.scrumpoker.viewModel

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UserViewModel @Inject
constructor() : ViewModel() {
    val user: String
        get() = "working"
}
