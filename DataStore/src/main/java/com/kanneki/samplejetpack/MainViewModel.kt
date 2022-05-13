package com.kanneki.samplejetpack

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataStore: DataStore = DataStore()
): ViewModel() {

    var saveDataValue by mutableStateOf("")
    var textValue by mutableStateOf("")
    var sendValue by mutableStateOf("")

    init {
        getSaveValue()
    }

    fun setNewTextValue(value: String) {
        textValue = value
    }

    fun sendData() {
        if (textValue.isNotBlank()) {
            sendValue = "Save Data"
            viewModelScope.launch {
                dataStore.insertCounter(textValue)
            }
        } else {
            sendValue = "Data value is null"
            getSaveValue()
        }
    }

    fun getSaveValue() {
        viewModelScope.launch {
            dataStore.getCounterFlow().collectLatest {
                saveDataValue = it
            }
        }
    }
}