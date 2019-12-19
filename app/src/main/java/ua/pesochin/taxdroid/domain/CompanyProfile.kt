package ua.pesochin.taxdroid.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import ua.pesochin.taxdroid.util.toDateString

@Entity
@Parcelize
data class CompanyProfile(

    @PrimaryKey
    @ColumnInfo(name = "edrpou_code")
    val edrpouCode: Int,

    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "registration_date")
    val registrationDate: Long,

    @ColumnInfo(name = "authorized_person_name")
    val authorizedPersonName: String,

    @ColumnInfo(name = "authorized_capital")
    val authorizedCapital: Int,

    @ColumnInfo(name = "contact_info")
    val contactInfo: String
) : Parcelable {

    fun isValid(): Boolean {
        return edrpouCode > 0 &&
                fullName.isNotBlank() &&
                authorizedPersonName.isNotBlank() &&
                registrationDate > 0 &&
                authorizedCapital >= 0 &&
                contactInfo.isNotBlank()
    }

    override fun toString(): String {
        return "EDRPOU = $edrpouCode \n" +
                "name = $fullName \n" +
                "founder = $authorizedPersonName \n" +
                "registration date = ${registrationDate.toDateString()} \n" +
                "authorized capital = $authorizedCapital \n" +
                "contact info = $contactInfo"
    }

    companion object {
        const val FIZ_OSOBA_AUTH_PERSON_NAME = "ФІЗИЧНА ОСОБА"
    }
}