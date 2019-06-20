package com.rezkyatinnov.jeniuscontact.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.rezkyatinnov.jeniuscontact.databinding.ListitemMainBinding
import com.rezkyatinnov.jeniuscontact.model.Contact
import com.rezkyatinnov.jeniuscontact.utils.getParentActivity

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class MainListItemViewHolder(var binding: ListitemMainBinding) : RecyclerView.ViewHolder(binding.root) {

    private val viewModel: MainListItemViewModel = MainListItemViewModel(binding.root.getParentActivity() as MainActivity)

    fun bind(contact: Contact){
        viewModel.bind(contact)
        binding.viewModel = viewModel
    }
}
