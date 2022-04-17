package com.example.flights.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flights.R
import com.example.flights.databinding.FlightItemBinding
import com.example.flights.domain.models.Flight

class HistoryOfFlightsViewHolder(
    private val binding: FlightItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup) =
            HistoryOfFlightsViewHolder(
                FlightItemBinding.bind(LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.flight_item,
                        parent,
                        false
                    )
                )
            )
    }

    fun bindItem(flight: Flight) {
        flight.apply {
            binding.departureCity.text = departureCity
            binding.arrivalCity.text = arrivalCity
            binding.departureTime.text = departureTime
            binding.arrivalTime.text = arrivalTime
            binding.passportNumber.text = passportNumber
            binding.passengerName.text = passengerName
            binding.ageCategory.text = ageCategory.category
        }
    }
}