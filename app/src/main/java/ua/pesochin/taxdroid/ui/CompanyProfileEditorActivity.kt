package ua.pesochin.taxdroid.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_company_profile_editor.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.pesochin.taxdroid.R
import ua.pesochin.taxdroid.TaxApp
import ua.pesochin.taxdroid.domain.CompaniesProfileRepository
import ua.pesochin.taxdroid.domain.CompanyProfile
import ua.pesochin.taxdroid.domain.PreferenceRepository
import ua.pesochin.taxdroid.util.*

class CompanyProfileEditorActivity : AppCompatActivity() {

    private var profileRepository: CompaniesProfileRepository? = null

    private var preferenceRepository: PreferenceRepository? = null

    private var profile: CompanyProfile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_profile_editor)

        profileRepository = (application as TaxApp).profileRepository
        preferenceRepository = (application as TaxApp).preferenceRepository

        getCompanyProfile()?.let {
            profile = it

            fullNameEditText.setText(it.fullName)
            edrpouCodeEditText.setText(it.edrpouCode.toString())
            registrationDateEditText.setText(it.registrationDate.toDateString())
            authorizedPersonNameEditText.setText(it.authorizedPersonName)
            authorizedCapitalEditText.setText(it.authorizedCapital.toString())
            contactInfoEditText.setText(it.contactInfo)
        } ?: Log.e("###", "expected non-null profile")


        backButton.setOnClickListener {
            finish()
        }

        sendMailButton.setOnClickListener {
            sendMail()
        }

        removeButton.setOnClickListener {
            removeProfile()
        }

        updateButton.setOnClickListener {
            updateProfile()
        }

    }

    private fun sendMail() {
        val inputProfile = getInputProfile()

        if (inputProfile.isValid()) {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("sms:${preferenceRepository?.getPhoneNumber()}")
            )

            intent.putExtra("sms_body", inputProfile.toString())

            startActivity(intent)
        } else {
            showErrorSnackbar(
                view = sendMailButton,
                errorMessage = "invalid company profile"
            )
        }
    }

    private fun removeProfile() {
        profile?.let {
            val job = GlobalScope.launch(Dispatchers.IO) {
                profileRepository?.remove(it)
            }

            job.invokeOnCompletion { finish() }
        }
    }

    private fun updateProfile() {
        val inputProfile = getInputProfile()

        if (inputProfile.isValid()) {
            val job = GlobalScope.launch(Dispatchers.IO) {
                profileRepository?.update(inputProfile)
            }

            job.invokeOnCompletion { finish() }
        } else {
            showErrorSnackbar(
                view = updateButton,
                errorMessage = "invalid company profile"
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
}