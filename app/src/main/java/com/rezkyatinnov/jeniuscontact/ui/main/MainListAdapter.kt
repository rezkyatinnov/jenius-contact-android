package com.rezkyatinnov.jeniuscontact.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rezkyatinnov.jeniuscontact.R
import com.rezkyatinnov.jeniuscontact.databinding.ListitemMainBinding
import com.rezkyatinnov.jeniuscontact.model.Contact


/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class MainListAdapter(val context: Context, var data: List<Contact>) : RecyclerView.Adapter<MainListItemViewHolder>(){
    private var inflater: LayoutInflater? = null
    init {
        inflater = LayoutInflater.from(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListItemViewHolder {
        val binding: ListitemMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.listitem_main,parent,false)
        return MainListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainListItemViewHolder, position: Int) {
        val itemdata = data[position]
        holder.bind(itemdata)
    }

    fun updateData(data:List<Contact>){
        this.data = data
        notifyDataSetChanged()
    }

}
