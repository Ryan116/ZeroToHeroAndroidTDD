package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

interface ListLiveDataWrapper {

    fun update(list: List<CharSequence>)
    fun save(bundle: BundleWrapper.Save)
    fun liveData(): LiveData<List<CharSequence>>

    fun add(new: CharSequence)

    class Base(
        private val liveData: MutableLiveData<ArrayList<CharSequence>> = SingleLiveEvent()
    ) : ListLiveDataWrapper {
        override fun update(list: List<CharSequence>) {
            liveData.value = ArrayList(list)
        }

        override fun liveData(): LiveData<List<CharSequence>> {
            return liveData.map { it.toList() }
        }

        override fun add(new: CharSequence) {
            val currentList = liveData.value ?: ArrayList()
            currentList.add(new)
            update(currentList)
        }

        override fun save(bundle: BundleWrapper.Save) {
            liveData.value?.let {
                bundle.save(it)
            }
        }
    }
}
