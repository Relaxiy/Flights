package com.example.flights.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flights.data.room.dao.FlightsDao
import com.example.flights.data.room.entity.FlightEntity
import com.example.flights.utils.constants.Constants

@Database(
    entities = [FlightEntity::class],
    version = Constants.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFlightsDao(): FlightsDao
}