package com.rezkyatinnov.jeniuscontact.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rezkyatinnov.jeniuscontact.ui.addcontact.AddContactActivity
import com.rezkyatinnov.jeniuscontact.ui.addcontact.AddContactViewModel
import com.rezkyatinnov.jeniuscontact.ui.detail.DetailActivity
import com.rezkyatinnov.jeniuscontact.ui.detail.DetailViewModel
import com.rezkyatinnov.jeniuscontact.ui.main.MainActivity
import com.rezkyatinnov.jeniuscontact.ui.main.MainViewModel
import javax.inject.Inject

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


class ViewModelFactory @Inject constructor(var appCompatActivity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(appCompatActivity as MainActivity) as T
        } else if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(appCompatActivity as DetailActivity) as T
        } else if(modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddContactViewModel(appCompatActivity as AddContactActivity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
