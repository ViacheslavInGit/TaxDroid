package ua.pesochin.taxdroid

import android.app.Application
import androidx.room.Room
import ua.pesochin.taxdroid.data.preferences.PreferenceRepositoryImpl
import ua.pesochin.taxdroid.data.preferences.SharedPreferencesDao
import ua.pesochin.taxdroid.data.profile.CompaniesProfileRepositoryImpl
import ua.pesochin.taxdroid.data.profile.TaxDatabase
import ua.pesochin.taxdroid.domain.CompaniesProfileRepository
import ua.pesochin.taxdroid.domain.PreferenceRepository

class TaxApp : Application() {

    lateinit var profileRepository: CompaniesProfileRepository

    lateinit var preferenceRepository: PreferenceRepository

    override fun onCreate() {
        super.onCreate()

        val database = Room.databaseBuilder(
            applicationContext,
            TaxDatabase::class.java,
            "tax-database"
        ).build()

        val profileDao = database.getProfileDao()

        profileRepository = CompaniesProfileRepositoryImpl(profileDao)


        val preferencesDao = SharedPreferencesDao(applicationContext)

        preferenceRepository = PreferenceRepositoryImpl(preferencesDao)
    }

}