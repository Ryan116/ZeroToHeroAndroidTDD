package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

interface UiState: Serializable {

    data object ShowProgress : UiState

    data object ShowData : UiState
}
