package com.example.flights.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flights.domain.models.enums.AgeCategory

@Entity(tableName = "flights")
data class FlightEntity(
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0,
    @ColumnInfo(name = "departure_city")
    val departureCity: String,
    @ColumnInfo(name = "arrival_city")
    val arrivalCity: String,
    @ColumnInfo(name = "departure_time")
    val departureTime: String,
    @ColumnInfo(name = "arrival_time")
    val arrivalTime: String,
    @ColumnInfo(name = "passport_number")
    val passportNumber: String,
    @ColumnInfo(name = "passenger_name")
    val passengerName: String,
    @ColumnInfo(name = "age_category")
    val ageCategory: AgeCategory
)
