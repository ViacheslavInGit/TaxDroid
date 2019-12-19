package ua.pesochin.taxdroid.data.preferences

import ua.pesochin.taxdroid.domain.PreferenceRepository

class PreferenceRepositoryImpl(
    private val preferencesDao: SharedPreferencesDao
) : PreferenceRepository {

    override fun getPhoneNumber(): String? {
        return preferencesDao.getPhoneNumber()
    }

    override fun savePhoneNumber(phoneNumber: String) {
        preferencesDao.savePhoneNumber(phoneNumber)
    }


}