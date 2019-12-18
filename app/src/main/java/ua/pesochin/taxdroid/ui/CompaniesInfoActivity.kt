package ua.pesochin.taxdroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_companies_info.*
import kotlinx.coroutines.*
import ua.pesochin.taxdroid.R
import ua.pesochin.taxdroid.TaxApp
import ua.pesochin.taxdroid.domain.CompaniesProfileRepository
import ua.pesochin.taxdroid.domain.CompanyProfile
import kotlin.math.absoluteValue
import kotlin.random.Random

class CompaniesInfoActivity : AppCompatActivity() {

    private val recyclerAdapter = CompaniesInfoAdapter()

    private var profileRepository: CompaniesProfileRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_companies_info)

        companiesInfoRecycler.layoutManager = LinearLayoutManager(this)
        companiesInfoRecycler.adapter = recyclerAdapter

        profileRepository = (application as TaxApp).profileRepository

        //add mock data
        GlobalScope.launch(Dispatchers.IO) {
            profileRepository?.add(
                CompanyProfile(
                    edrpouCode = Random.nextInt().absoluteValue % 100_000_000,
                    fullName = "fullName",
                    registrationDate = System.currentTimeMillis(),
                    authorizedPersonName = "Ivanov Dolboeb",
                    authorizedCapital = 40,
                    contactInfo = "+38012311223"
                )
            )
        }
    }


    @ExperimentalCoroutinesApi
    override fun onResume() {
        super.onResume()

        GlobalScope.launch(Dispatchers.IO) {

            val profiles = GlobalScope.async {
                profileRepository?.getAll()
            }

            GlobalScope.launch(Dispatchers.Main) {
                profiles.await()?.let {
                    recyclerAdapter.setProfileList(it)
                }
            }

        }


    }


}
