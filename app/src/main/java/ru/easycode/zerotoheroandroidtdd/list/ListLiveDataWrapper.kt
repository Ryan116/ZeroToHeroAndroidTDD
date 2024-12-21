package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    interface Read {
        fun liveData(): LiveData<List<String>>
    }

    interface Update {
        fun update(value: List<String>)
    }

    interface Mutable: Read, Update

    interface Add {
        fun add(value: String)
    }

    interface All: Mutable, Add

    class Base:  All {

        private val liveData = MutableLiveData<List<String>>()

        override fun update(value: List<String>) {
            liveData.value = value
        }

        override fun liveData(): LiveData<List<String>> {
            return liveData
        }

        override fun add(value: String) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            update(list)
        }
    }
}