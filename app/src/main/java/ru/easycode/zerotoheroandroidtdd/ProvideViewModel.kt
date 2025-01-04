package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base(
        core: Core,
        private val clearViewModel: ClearViewModel
    ) : ProvideViewModel {

        private val repository = Repository.Base(core.dao(), Now.Base())
        private val liveDataWrapper = ListLiveDataWrapper.Base()
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(repository, liveDataWrapper)
                AddViewModel::class.java -> AddViewModel(repository, liveDataWrapper, clearViewModel)
                else -> DeleteViewModel(liveDataWrapper, repository, clearViewModel)
            } as T
        }

    }
}