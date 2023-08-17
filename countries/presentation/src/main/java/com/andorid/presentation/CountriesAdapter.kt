package com.andorid.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andorid.domain.model.Country
import com.andorid.presentation.databinding.ViewHolderCountryBinding


class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.MyViewHolder>() {

    private var list = listOf<Country>()

    fun setData(list: List<Country>) {
        this.list = list
        notifyItemInserted(this.list.lastIndex)
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderCountryBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ViewHolderCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.apply {
            val item = list[position]
            countryName.text = item.name
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}