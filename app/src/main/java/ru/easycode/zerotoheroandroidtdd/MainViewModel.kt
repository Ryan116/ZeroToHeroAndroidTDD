package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
): ViewModel(), ListLiveDataWrapper.Read {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun liveData() = liveDataWrapper.liveData()

    fun init() {
        viewModelScope.launch(dispatcher) {
            val value = repository.list().map { ItemUi(id = it.id, text = it.text) }
            withContext(dispatcherMain) {
                liveDataWrapper.update(value)
            }
        }
    }
}
