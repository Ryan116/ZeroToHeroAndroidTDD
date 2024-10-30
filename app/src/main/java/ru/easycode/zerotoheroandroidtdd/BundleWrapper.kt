package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle

interface BundleWrapper {

    interface Mutable: Save, Restore
    interface Save {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore {
        fun restore(): List<CharSequence>
    }

    class Base(private val bundle: Bundle): Mutable {
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY, ArrayList(list))
        }

        override fun restore(): ArrayList<CharSequence> {
            return bundle.getCharSequenceArrayList(KEY) as ArrayList<CharSequence>
        }

        companion object {
            const val KEY = "list_char_sequence"
        }
    }
}
