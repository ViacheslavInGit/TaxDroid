package ua.pesochin.taxdroid.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import ua.pesochin.taxdroid.domain.CompanyProfile

private const val companyProfileKey = "companyProfileKey"

fun Context.navigateTo(
    targetClass: Class<out Activity>,
    companyProfile: CompanyProfile? = null
) {
    val intent = Intent(this, targetClass)

    intent.putExtra(companyProfileKey, companyProfile)

    startActivity(intent)
}

fun Activity.getCompanyProfile(): CompanyProfile? {
    return this.intent.getParcelableExtra(companyProfileKey)
}