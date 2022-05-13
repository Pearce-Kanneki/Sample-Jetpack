package com.kanneki.samplejetpack

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore {

    companion object {
        val KEY_COUNTER = stringPreferencesKey("key")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")

    /** Get Data */
    fun getCounterFlow(): Flow<String> = MyApp.getContext().dataStore.data
        .map { preferences ->
            preferences[KEY_COUNTER] ?: ""
        }

    /** Edit Data */
    suspend fun insertCounter(value: String) {
        MyApp.getContext().dataStore.edit { setting ->
            setting[KEY_COUNTER] = value
        }
    }
}