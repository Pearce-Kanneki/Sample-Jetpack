package com.kanneki.room

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanneki.room.data.data_source.ShowDataDatabase
import com.kanneki.room.data.repository.ShowDataRepositoryIml
import com.kanneki.room.domain.model.ShowData
import com.kanneki.room.domain.repository.ShowDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ShowDataRepository = ShowDataRepositoryIml()
): ViewModel() {

    var items = mutableStateListOf<ShowData>()

    init {
        searchData()
    }

    fun searchData() {
        viewModelScope.launch {
            repository.getData().collectLatest {
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
        }
    }
}

sealed class Event {
    object Add: Event()
}