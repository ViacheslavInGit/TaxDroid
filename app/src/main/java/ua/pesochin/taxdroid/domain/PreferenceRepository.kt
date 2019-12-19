package ua.pesochin.taxdroid.domain

interface PreferenceRepository {

    fun getPhoneNumber(): String?

    fun savePhoneNumber(phoneNumber: String)
}