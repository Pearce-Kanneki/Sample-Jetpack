package com.kanneki.room.domain.repository

import com.kanneki.room.domain.model.ShowData
import kotlinx.coroutines.flow.Flow

interface ShowDataRepository {

    fun getData(): Flow<List<ShowData>>

    suspend fun insertData(data: ShowData)

    suspend fun insertList(list: List<ShowData>)

    suspend fun updateData(data: ShowData)

    suspend fun deleteData(data: ShowData)

    suspend fun deleteList(list: List<ShowData>)
}