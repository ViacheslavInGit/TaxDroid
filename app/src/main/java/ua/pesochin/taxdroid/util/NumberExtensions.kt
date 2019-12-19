package ua.pesochin.taxdroid.util

import android.util.Log
import android.widget.TextView

fun TextView.parseInt(defaultValue: Int): Int {
    return try {
        this.text.toString().toInt()
    } catch (e: Exception) {
        Log.e("###", e.message ,e)
        defaultValue
    }
}