package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.Core
import ru.easycode.zerotoheroandroidtdd.Now
import ru.easycode.zerotoheroandroidtdd.Repository
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.AddViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

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
                else -> throw IllegalStateException("unknown viewModelClass $viewModelClass")
            } as T
        }

    }
}