package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ProvideViewModel
    private val store = HashMap<Class<out ViewModel>, ViewModel?>()

    private val clear = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            store[clasz] = null
        }
    }

    override fun onCreate() {
        super.onCreate()
        val core = Core(this)
        factory = ProvideViewModel.Base(core, clear)
    }
    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        if (store[viewModelClass] == null) {
            store[viewModelClass] = factory.viewModel(viewModelClass)
        }
        return store[viewModelClass] as T
    }

}