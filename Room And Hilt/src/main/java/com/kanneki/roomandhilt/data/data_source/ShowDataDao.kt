package com.kanneki.roomandhilt.data.data_source

import androidx.room.*
import com.kanneki.roomandhilt.domain.model.ShowData
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowDataDao {

    @Query("SELECT * FROM ${ShowDataDatabase.TABLE_NAME}")
    fun getList(): Flow<List<ShowData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: ShowData)

    @Update
    suspend fun updateData(data: ShowData)

    @Delete
    suspend fun deleteData(data: ShowData)

    /** List */
    @Insert
    suspend fun insetList(list: List<ShowData>)

    @Delete
    suspend fun deleteList(list: List<ShowData>)
}