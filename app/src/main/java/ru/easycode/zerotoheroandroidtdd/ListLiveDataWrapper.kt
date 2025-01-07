package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    interface Read {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Update {
        fun update(list: List<ItemUi>)
    }

    interface Mutable: Read, Update

    interface Add {
        fun add(value: ItemUi)
    }

    interface Change {
        fun delete(itemUi: ItemUi)
        fun update(itemUi: ItemUi)
    }

    interface All: Mutable, Add, Change

    class Base: All {

        private val liveData = MutableLiveData<List<ItemUi>>()

        override fun update(list: List<ItemUi>) {
            liveData.value = list
        }

        override fun update(itemUi: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.find { it.areItemsSame(itemUi) }?.let {
                list[list.indexOf(it)] = itemUi
            }
            update(list)
        }

        override fun liveData(): LiveData<List<ItemUi>> {
            return liveData
        }

        override fun add(value: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            update(list)
        }

        override fun delete(itemUi: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.remove(itemUi)
            update(list)
        }
    }
}