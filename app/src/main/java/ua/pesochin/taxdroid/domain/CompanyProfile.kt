package ua.pesochin.taxdroid.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
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
) {

    companion object {
        const val FIZ_OSOBA_AUTH_PERSON_NAME = "ФІЗИЧНА ОСОБА"
    }
}