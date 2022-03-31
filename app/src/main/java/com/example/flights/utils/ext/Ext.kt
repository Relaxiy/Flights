package com.example.flights.utils.ext

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.flights.FlightsApplication
import com.example.flights.di.AppComponent
import com.example.flights.domain.models.Flight
import com.example.flights.presentation.dialog.MessageDialog
import kotlin.concurrent.thread

fun FragmentActivity.openFragment(tag: String, fragment: Fragment, id: Int) {
    supportFragmentManager
        .beginTransaction()
        .replace(id, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FlightsApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

fun FragmentActivity.dialog(message: String, deleteFlight: () -> Unit) {
    val myDialogFragment = MessageDialog(message) {
        deleteFlight()
    }
    val manager = supportFragmentManager
    myDialogFragment.show(manager, "myDialog")
}

fun String.isPassportNumber(): Boolean {
    return if (this.length == 7) {
        val letters = this.substring(0, 2)
        val numbers = this.substring(2)
        var counter = 0
        for (it in numbers) {
            if (it.code in 0..9) {
                counter++
            }
        }
        counter == 5 || letters.uppercase() == letters
    } else {
        false
    }
}
