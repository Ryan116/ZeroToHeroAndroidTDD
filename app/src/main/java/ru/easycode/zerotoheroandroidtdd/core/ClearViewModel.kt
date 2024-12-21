package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {

    fun clearViewModel(clasz: Class<out ViewModel>)
}