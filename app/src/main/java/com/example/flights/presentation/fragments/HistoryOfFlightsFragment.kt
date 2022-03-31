package com.example.flights.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.flights.R
import com.example.flights.domain.models.Flight
import com.example.flights.presentation.recycler.HistoryOfFlightsAdapter
import com.example.flights.presentation.recycler.clickListener.DeleteClickListener
import com.example.flights.presentation.viewModels.CreateFlightsFragmentViewModel
import com.example.flights.presentation.viewModels.HistoryOfFlightsFragmentViewModel
import com.example.flights.utils.actionSelector.ActionSelector
import com.example.flights.utils.ext.appComponent
import com.example.flights.utils.ext.dialog
import kotlinx.android.synthetic.main.fragment_history_of_flights.*

class HistoryOfFlightsFragment : Fragment(R.layout.fragment_history_of_flights) {

    companion object {
        const val TAG = "HistoryOfFlightsFragment"
        fun newInstance() = HistoryOfFlightsFragment()
    }

    private val historyOfFlightsFragmentViewModel by viewModels<HistoryOfFlightsFragmentViewModel> {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val recycler by lazy {
        view?.findViewById<RecyclerView>(R.id.flightsHistoryRecycler)
    }

    private val adapter by lazy {
        HistoryOfFlightsAdapter(deleteClickListener)
    }

    private val deleteClickListener by lazy {
        object : DeleteClickListener {
            override fun deleteItem(flight: Flight) {
                requireActivity().dialog("Are you sure? This action cannot be undone.") {
                    historyOfFlightsFragmentViewModel.deleteFlight(flight)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun observer() {
        recycler?.adapter = adapter
        historyOfFlightsFragmentViewModel.flights.observe(viewLifecycleOwner) { flights ->
            adapter.setItems(flights)
        }
    }
}