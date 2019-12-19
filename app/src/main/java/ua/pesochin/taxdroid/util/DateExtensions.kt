package ua.pesochin.taxdroid.util

import android.icu.text.SimpleDateFormat
import android.util.Log
import android.widget.TextView

val format = SimpleDateFormat("dd/mm/yyyy")

fun TextView.parseDate(defaultValue: Long): Long {
    return try {
        format.parse(this.text.toString()).time
    } catch (e: Exception) {
        Log.e("###", e.message ,e)
        defaultValue
    }
}