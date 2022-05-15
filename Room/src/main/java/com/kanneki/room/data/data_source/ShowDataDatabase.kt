package com.kanneki.room.data.data_source

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kanneki.room.MyApp
import com.kanneki.room.domain.model.ShowData

@Database(entities = [(ShowData::class)], version = 1)
abstract class ShowDataDatabase : RoomDatabase() {

    companion object {
        const val TABLE_NAME = "show_data"
        private var instance: ShowDataDatabase? = null
        private val LOCK = Any()

        fun getInstance(): ShowDataDatabase = instance ?: synchronized(LOCK) {
            instance ?: buildDataBase().also { instance = it }
        }

        private fun buildDataBase() = Room.databaseBuilder(
            MyApp.getContext(),
            ShowDataDatabase::class.java,
            TABLE_NAME
        ).build()
    }

    abstract fun getShowDataDao(): ShowDataDao
}