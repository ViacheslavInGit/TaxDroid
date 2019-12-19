package ua.pesochin.taxdroid.ui

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_companies_info.*
import kotlinx.coroutines.*
import ua.pesochin.taxdroid.R
import ua.pesochin.taxdroid.TaxApp
import ua.pesochin.taxdroid.domain.CompaniesProfileRepository
import ua.pesochin.taxdroid.domain.PreferenceRepository
import ua.pesochin.taxdroid.util.navigateTo

class CompaniesProfileListActivity : AppCompatActivity() {

    private val recyclerAdapter = CompaniesProfileListAdapter()

    private var profileRepository: CompaniesProfileRepository? = null

    private var preferenceRepository: PreferenceRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_companies_info)

        companiesInfoRecycler.layoutManager = LinearLayoutManager(this)
        companiesInfoRecycler.adapter = recyclerAdapter

        profileRepository = (application as TaxApp).profileRepository
        preferenceRepository = (application as TaxApp).preferenceRepository

        newCompanyProfileButton.setOnClickListener {
            navigateTo(NewProfileActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {

            R.id.setPhoneNumberMenuItem -> {

                showInputPhoneDialog()
                true
            }

            else -> {
                Log.d("###", "untreated menu item $item")
                super.onOptionsItemSelected(item)
            }

        }
    }

    private fun showInputPhoneDialog() {

        val inputView = EditText(this)
        inputView.inputType = InputType.TYPE_CLASS_PHONE

        preferenceRepository?.getPhoneNumber()?.let {
            inputView.setText(it)
        }

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.set_phone_number))
            .setView(inputView)
            .setPositiveButton(R.string.update) { _, _ ->
                run {
                    preferenceRepository?.savePhoneNumber(inputView.text.toString())
                }
            }
            .show()
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
