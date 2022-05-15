package com.kanneki.room.data.repository

import com.kanneki.room.data.data_source.ShowDataDatabase
import com.kanneki.room.domain.model.ShowData
import com.kanneki.room.domain.repository.ShowDataRepository
import kotlinx.coroutines.flow.Flow

class ShowDataRepositoryIml: ShowDataRepository {

    private val database: ShowDataDatabase = ShowDataDatabase.getInstance()

    override fun getData(): Flow<List<ShowData>> {
        return database.getShowDataDao().getList()
    }

    override suspend fun insertData(data: ShowData) {
        database.getShowDataDao().insertData(data)
    }

    override suspend fun insertList(list: List<ShowData>) {
        database.getShowDataDao().insetList(list)
    }

    override suspend fun updateData(data: ShowData) {
        database.getShowDataDao().updateData(data)
    }

    override suspend fun deleteData(data: ShowData) {
        database.getShowDataDao().deleteData(data)
    }

    override suspend fun deleteList(list: List<ShowData>) {
        database.getShowDataDao().deleteList(list)
    }
}