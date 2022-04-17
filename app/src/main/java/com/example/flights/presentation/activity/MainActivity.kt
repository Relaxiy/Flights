package com.example.flights.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flights.R
import com.example.flights.presentation.fragments.CreateFlightsFragment
import com.example.flights.utils.ext.openFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        openFragment(CreateFlightsFragment.TAG, CreateFlightsFragment.newInstance(), R.id.container)
    }
}