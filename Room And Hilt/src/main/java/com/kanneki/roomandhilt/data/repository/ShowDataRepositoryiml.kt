package com.kanneki.roomandhilt.data.repository

import com.kanneki.roomandhilt.data.data_source.ShowDataDao
import com.kanneki.roomandhilt.domain.model.ShowData
import com.kanneki.roomandhilt.domain.repository.ShowDataRepository
import kotlinx.coroutines.flow.Flow

class ShowDataRepositoryiml(
    private val dao: ShowDataDao
): ShowDataRepository {

    override fun getData(): Flow<List<ShowData>> {
        return dao.getList()
    }

    override suspend fun insertData(data: ShowData) {
        dao.insertData(data)
    }

    override suspend fun insertList(list: List<ShowData>) {
        dao.insetList(list)
    }

    override suspend fun updateData(data: ShowData) {
        dao.updateData(data)
    }

    override suspend fun deleteData(data: ShowData) {
        dao.deleteData(data)
    }

    override suspend fun deleteList(list: List<ShowData>) {
        dao.deleteList(list)
    }


}