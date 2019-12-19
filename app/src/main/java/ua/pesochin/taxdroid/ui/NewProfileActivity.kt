package ua.pesochin.taxdroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.pesochin.taxdroid.R
import ua.pesochin.taxdroid.TaxApp
import ua.pesochin.taxdroid.domain.CompaniesProfileRepository
import ua.pesochin.taxdroid.domain.CompanyProfile
import ua.pesochin.taxdroid.util.parseDate
import ua.pesochin.taxdroid.util.parseInt
import ua.pesochin.taxdroid.util.showErrorSnackbar

class NewProfileActivity : AppCompatActivity() {

    private var profileRepository: CompaniesProfileRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_profile)

        profileRepository = (application as TaxApp).profileRepository

        updateButton.setOnClickListener {
            if (isInputProfileValid()) {
                saveInputProfile()
                this.finish()
            } else {
                showErrorSnackbar(
                    view = updateButton,
                    errorMessage = "invalid company profile"
                )
            }
        }

        backButton.setOnClickListener {
            this.finish()
        }
    }

    private fun saveInputProfile() {
        GlobalScope.launch(Dispatchers.IO) {
            profileRepository?.add(
                getInputProfile()
            )
        }
    }

    private fun getInputProfile(): CompanyProfile {
        return CompanyProfile(
            edrpouCode = edrpouCodeEditText.parseInt(defaultValue = -1),
            fullName = fullNameEditText.text.toString(),
            registrationDate = registrationDateEditText.parseDate(defaultValue = -1),
            authorizedPersonName = authorizedPersonNameEditText.text.toString(),
            authorizedCapital = authorizedCapitalEditText.parseInt(defaultValue = -1),
            contactInfo = contactInfoEditText.text.toString()
        )
    }

    private fun isInputProfileValid(): Boolean = getInputProfile().isValid()

}