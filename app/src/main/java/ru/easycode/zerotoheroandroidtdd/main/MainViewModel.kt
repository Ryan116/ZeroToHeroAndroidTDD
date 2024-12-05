package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
): ViewModel(), Navigation.Read {

    override fun liveData(): LiveData<Screen> {
        return navigation.liveData()
    }
    fun init(firstRun: Boolean) {
        if (firstRun) {
            navigation.update(ListScreen)
        }
    }
}
