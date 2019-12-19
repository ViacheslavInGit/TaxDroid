package ua.pesochin.taxdroid.data.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesDao(
    private val context: Context
) {

    private val preferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_FILE_NAME,
        Context.MODE_PRIVATE
    )

    private val editor = preferences.edit()

    fun getPhoneNumber(): String? {
        return preferences.getString(
            PHONE_NUMBER_KEY,
            DEFAULT_PHONE_NUMBER
        )
    }

    fun savePhoneNumber(phoneNumber: String) {
        editor.putString(
            PHONE_NUMBER_KEY,
            phoneNumber
        )

        editor.apply()
    }

    companion object {
        const val PREFERENCES_FILE_NAME = "preferences"

        const val PHONE_NUMBER_KEY = "phone_number"
        const val DEFAULT_PHONE_NUMBER = "+380508155733"
    }
}