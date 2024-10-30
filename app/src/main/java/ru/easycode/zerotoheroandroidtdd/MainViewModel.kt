package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

class MainViewModel(
    private val listLiveDataWrapper: ListLiveDataWrapper
) {
    fun getLiveData(): LiveData<List<CharSequence>> {
        return listLiveDataWrapper.liveData()
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        val list = bundle.restore()
        listLiveDataWrapper.update(list)
    }

    fun add(text: CharSequence) {
        listLiveDataWrapper.add(text)
    }
}
