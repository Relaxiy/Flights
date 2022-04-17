package com.example.flights.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flights.data.room.dao.FlightsDao
import com.example.flights.data.room.entity.FlightEntity
import com.example.flights.database.AppDatabase.Companion.DATABASE_VERSION

@Database(
    entities = [FlightEntity::class],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        const val DATABASE_VERSION = 1
    }
    abstract fun getFlightsDao(): FlightsDao
}