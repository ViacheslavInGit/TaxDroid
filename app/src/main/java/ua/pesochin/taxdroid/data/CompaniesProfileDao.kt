package ua.pesochin.taxdroid.data

import androidx.room.*
import ua.pesochin.taxdroid.domain.CompanyProfile

@Dao
interface CompaniesProfileDao {

    @Insert
    suspend fun insert(profile: CompanyProfile)

    @Update
    suspend fun update(profile: CompanyProfile)

    @Delete
    suspend fun delete(profile: CompanyProfile)

    @Query("SELECT * FROM CompanyProfile")
    suspend fun getAll(): List<CompanyProfile>
}