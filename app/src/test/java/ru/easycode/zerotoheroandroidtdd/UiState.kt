package ru.easycode.zerotoheroandroidtdd

interface UiState {

        fun subscribeState()
        data object ShowProgress: UiState {
                override fun subscribeState() {
                        
                }

        }
        data object ShowData: UiState {
                override fun subscribeState() {
                        TODO("Not yet implemented")
                }
        }
}
