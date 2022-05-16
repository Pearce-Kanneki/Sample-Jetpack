package com.kanneki.roomandhilt.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kanneki.roomandhilt.domain.model.ShowData

@Database(entities = [ShowData::class], version = 1)
abstract class ShowDataDatabase: RoomDatabase() {

    abstract val showDataDao: ShowDataDao

    companion object {
        const val TABLE_NAME = "show_data"
    }
}