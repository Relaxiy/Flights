package com.example.flights.presentation.recycler.clickListener

import com.example.flights.domain.models.Flight

interface DeleteClickListener {
    fun deleteItem(flight: Flight)
}