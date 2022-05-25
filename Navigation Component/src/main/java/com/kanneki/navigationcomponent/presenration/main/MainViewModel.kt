package com.kanneki.navigationcomponent.presenration.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kanneki.navigationcomponent.Screen

class MainViewModel: ViewModel() {

    var textValue by mutableStateOf("")

    fun setNewTextValue(value: String){
        textValue = value
    }

    fun checkScreen(): String {
        return if (textValue.isNotBlank()) {
            Screen.DetailScreen.route + "/$textValue"
        } else {
            Screen.NormalScreen.route
        }
    }
}