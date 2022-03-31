package com.example.flights.presentation.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flights.domain.models.Flight
import com.example.flights.presentation.recycler.clickListener.DeleteClickListener

class HistoryOfFlightsAdapter(
    private val deleteClickListener: DeleteClickListener
) : RecyclerView.Adapter<HistoryOfFlightsViewHolder>() {

    private var items = listOf<Flight>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryOfFlightsViewHolder {
        return HistoryOfFlightsViewHolder.newInstance(parent, deleteClickListener)
    }

    override fun onBindViewHolder(holder: HistoryOfFlightsViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: List<Flight>) {
        items = data
        notifyDataSetChanged()
    }
}