package com.kanneki.roomandhilt

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanneki.roomandhilt.domain.model.ShowData
import com.kanneki.roomandhilt.domain.repository.ShowDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ShowDataRepository
): ViewModel() {

    val items = mutableStateListOf<ShowData>()
    var showDialog = mutableStateOf(false)
    var selectData = mutableStateOf<ShowData?>(null)

    init {
        searchData()
    }

    fun searchData() {
        viewModelScope.launch {
            repository.getData().collectLatest {
                items.clear()
                items.addAll(it)
            }
        }
    }

    fun onEvent(event: Event) {
        when(event) {
            is Event.Add -> {
                viewModelScope.launch {
                    ShowData(title = "Test Title", desc = "Message").also {
                        repository.insertData(data = it)
                        searchData()
                    }
                }
            }
            is Event.Delete -> {
                viewModelScope.launch {
                    selectData.value?.let {
                        repository.deleteData(it)
                        searchData()
                    }
                }
            }
        }
    }
}

sealed class Event {
    object Add: Event()
    object Delete: Event()
}