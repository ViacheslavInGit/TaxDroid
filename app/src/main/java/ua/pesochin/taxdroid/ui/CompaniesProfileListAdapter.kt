package ua.pesochin.taxdroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_company_info.view.*
import ua.pesochin.taxdroid.R
import ua.pesochin.taxdroid.domain.CompanyProfile
import ua.pesochin.taxdroid.util.navigateTo

class CompaniesProfileListAdapter :
    RecyclerView.Adapter<CompaniesProfileListAdapter.InfoViewHolder>() {

    private val profilesList = mutableListOf<CompanyProfile>()

    fun setProfileList(profiles: List<CompanyProfile>) {
        profilesList.clear()
        profilesList.addAll(profiles)

        notifyDataSetChanged()
    }

    fun addCompanyProfile(profile: CompanyProfile) {
        profilesList.add(profile)

        notifyDataSetChanged()
    }

    fun addCompanyProfiles(profiles: List<CompanyProfile>) {
        profilesList.addAll(profiles)

        notifyDataSetChanged()
    }

    fun removeCompanyProfile(profile: CompanyProfile) {
        profilesList.remove(profile)

        notifyDataSetChanged()
    }

    fun updateData() {
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_company_info, parent, false)

        return InfoViewHolder(view)
    }

    override fun getItemCount() = profilesList.size

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {

        val profile = profilesList[position]

        holder.profile = profile

        holder.setCompanyFullName(
            profile.fullName
        )

        holder.setEdrpouCode(
            profile.edrpouCode
        )

    }

    class InfoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var profile: CompanyProfile? = null

        init {
            view.setOnClickListener {
                view.context.navigateTo(
                    targetClass = CompanyProfileEditorActivity::class.java,
                    companyProfile = profile
                )
            }
        }

        fun setEdrpouCode(edrpouCode: Int) {
            view.edrpouCode.text = edrpouCode.toString()
        }

        fun setCompanyFullName(fullName: String) {
            view.companyFullName?.text = fullName
        }

    }
}