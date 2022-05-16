package com.kanneki.roomandhilt.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kanneki.roomandhilt.data.data_source.ShowDataDatabase

@Entity(tableName = ShowDataDatabase.TABLE_NAME)
data class ShowData(
    @PrimaryKey(autoGenerate = true)
    val uuid: Int = 0,
    val title: String,
    val desc: String
)
