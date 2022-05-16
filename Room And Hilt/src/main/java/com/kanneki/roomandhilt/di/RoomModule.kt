package com.kanneki.roomandhilt.di

import android.app.Application
import androidx.room.Room
import com.kanneki.roomandhilt.data.data_source.ShowDataDao
import com.kanneki.roomandhilt.data.data_source.ShowDataDatabase
import com.kanneki.roomandhilt.data.repository.ShowDataRepositoryiml
import com.kanneki.roomandhilt.domain.repository.ShowDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideShowDataRepository(db: ShowDataDatabase): ShowDataRepository {
        return ShowDataRepositoryiml(db.showDataDao)
    }

    @Provides
    @Singleton
    fun provideShowDataDatabase(app: Application): ShowDataDatabase {
        return Room.databaseBuilder(
            app,
            ShowDataDatabase::class.java,
            ShowDataDatabase.TABLE_NAME
        ).build()
    }
}