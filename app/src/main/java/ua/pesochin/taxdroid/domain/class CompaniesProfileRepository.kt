package ua.pesochin.taxdroid.domain

interface CompaniesProfileRepository {

    suspend fun add(profile: CompanyProfile)
    suspend fun remove(profile: CompanyProfile)
    suspend fun update(profile: CompanyProfile)

    suspend fun getAll(): List<CompanyProfile>
}