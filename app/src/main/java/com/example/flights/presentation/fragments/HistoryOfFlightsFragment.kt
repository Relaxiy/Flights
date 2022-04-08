package com.example.flights.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flights.R
import com.example.flights.databinding.FragmentHistoryOfFlightsBinding
import com.example.flights.domain.models.Flight
import com.example.flights.presentation.recycler.HistoryOfFlightsAdapter
import com.example.flights.presentation.recycler.callback.SwipeToDeleteCallback
import com.example.flights.presentation.viewModels.HistoryOfFlightsFragmentViewModel
import com.example.flights.utils.ext.appComponent

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
        HistoryOfFlightsAdapter()
    }


    private val swipeToDeleteCallback by lazy {
        object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                recycler?.adapter?.notifyItemRemoved(position)
                historyOfFlightsFragmentViewModel.deleteFlight(position)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        recycler?.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recycler)
        historyOfFlightsFragmentViewModel.flights.observe(viewLifecycleOwner) { flights ->
            adapter.setItems(flights)
        }
    }
}