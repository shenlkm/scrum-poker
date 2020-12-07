package com.buildreams.scrumpoker.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


@Suppress("UNCHECKED_CAST")
class ViewModelFactory<T : ViewModel> @Inject constructor(private val vmp: Provider<T>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return vmp.get() as T
    }
}

