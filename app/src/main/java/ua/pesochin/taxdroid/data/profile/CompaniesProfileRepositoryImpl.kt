package ua.pesochin.taxdroid.data.profile

import ua.pesochin.taxdroid.domain.CompaniesProfileRepository
import ua.pesochin.taxdroid.domain.CompanyProfile

class CompaniesProfileRepositoryImpl(
    private val profileDao: CompaniesProfileDao
) : CompaniesProfileRepository {


    override suspend fun add(profile: CompanyProfile) {
        profileDao.insert(profile)
    }

    override suspend fun remove(profile: CompanyProfile) {
        profileDao.delete(profile)
    }

    override suspend fun update(profile: CompanyProfile) {
        profileDao.update(profile)
    }

    override suspend fun getAll(): List<CompanyProfile> {
        return profileDao.getAll()
    }

}