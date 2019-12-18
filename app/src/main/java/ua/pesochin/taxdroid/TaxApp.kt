package ua.pesochin.taxdroid

import android.app.Application
import androidx.room.Room
import ua.pesochin.taxdroid.data.CompaniesProfileDao
import ua.pesochin.taxdroid.data.CompaniesProfileRepositoryImpl
import ua.pesochin.taxdroid.data.TaxDatabase
import ua.pesochin.taxdroid.domain.CompaniesProfileRepository

class TaxApp : Application() {

    private lateinit var database: TaxDatabase

    private lateinit var profileDao: CompaniesProfileDao

    lateinit var profileRepository: CompaniesProfileRepository

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            TaxDatabase::class.java,
            "tax-database"
        ).build()

        profileDao = database.getProfileDao()

        profileRepository = CompaniesProfileRepositoryImpl(profileDao)
    }

}