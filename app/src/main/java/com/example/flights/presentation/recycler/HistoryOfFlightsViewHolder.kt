package com.example.flights.presentation.recycler

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flights.R
import com.example.flights.domain.models.Flight
import com.example.flights.presentation.recycler.clickListener.DeleteClickListener

class HistoryOfFlightsViewHolder(
    itemView: View,
    private val deleteClickListener: DeleteClickListener
) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun newInstance(parent: ViewGroup, deleteClickListener: DeleteClickListener) =
            HistoryOfFlightsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.flight_item,
                        parent,
                        false
                    ),
                deleteClickListener
            )
    }

    private val departureCityTextView by lazy {
        itemView.findViewById<TextView>(R.id.departure_city)
    }
    private val arrivalCityTextView by lazy {
        itemView.findViewById<TextView>(R.id.arrival_city)
    }
    private val departureTimeTextView by lazy {
        itemView.findViewById<TextView>(R.id.departure_time)
    }
    private val arrivalTimeTextView by lazy {
        itemView.findViewById<TextView>(R.id.arrival_time)
    }
    private val passportNumberTextView by lazy {
        itemView.findViewById<TextView>(R.id.passport_number)
    }
    private val passengerNameTextView by lazy {
        itemView.findViewById<TextView>(R.id.passenger_name)
    }
    private val ageCategoryTextView by lazy {
        itemView.findViewById<TextView>(R.id.age_category)
    }

    private val remove by lazy {
        itemView.findViewById<ImageView>(R.id.remove)
    }

    fun bindItem(flight: Flight) {
        flight.apply {
            departureCityTextView.text = departureCity
            arrivalCityTextView.text = arrivalCity
            departureTimeTextView.text = departureTime
            arrivalTimeTextView.text = arrivalTime
            passportNumberTextView.text = passportNumber
            passengerNameTextView.text = passengerName
            ageCategoryTextView.text = ageCategory.category
        }
        remove.setOnClickListener {
            deleteClickListener.deleteItem(flight)
        }
    }
}