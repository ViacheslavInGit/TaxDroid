package ua.pesochin.taxdroid.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.pesochin.taxdroid.domain.CompanyProfile

@Database(
    entities = [
        CompanyProfile::class
    ],
    version = 1
)
abstract class TaxDatabase : RoomDatabase() {

    abstract fun getProfileDao(): CompaniesProfileDao
}