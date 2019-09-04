package com.lambdaschool.basicandroidnetworking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.basicandroidnetworking.model.OceaniaCountry
import kotlinx.android.synthetic.main.country_item_layout.view.*

class RecyclerViewAdapter (val oceaniaCountries: MutableList<OceaniaCountry>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.tv_country_name
        val alpha2Code: TextView = view.tv_alpha2code
        val capital: TextView = view.tv_capital
        val subregion: TextView = view.tv_subregion
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.country_item_layout, parent, false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return oceaniaCountries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = oceaniaCountries[position].name
        holder.alpha2Code.text = "Country Code: ${oceaniaCountries[position].alpha2Code}"
        holder.capital.text = "Capital: ${oceaniaCountries[position].capital}"
        holder.subregion.text = "Subregion: ${oceaniaCountries[position].subregion}"
    }
}