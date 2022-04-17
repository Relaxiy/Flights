package com.example.flights.di

import android.content.Context
import androidx.room.Room
import com.example.flights.data.room.dao.FlightsDao
import com.example.flights.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .build()
    }

    @Singleton
    @Provides
    fun provideFlightsDao(database: AppDatabase): FlightsDao {
        return database.getFlightsDao()
    }

}