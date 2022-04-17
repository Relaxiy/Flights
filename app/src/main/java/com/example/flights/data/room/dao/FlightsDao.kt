package com.example.flights.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.flights.data.room.entity.FlightEntity

@Dao
interface FlightsDao {

    @Query("SELECT * FROM flights")
    suspend fun getAllFlights(): List<FlightEntity>

    @Insert
    suspend fun saveFlight(flightEntity: FlightEntity)

    @Delete
    suspend fun deleteFlight(flightEntity: FlightEntity)
}