package com.rezkyatinnov.jeniuscontact.ui.detail

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rezkyatinnov.jeniuscontact.R
import com.rezkyatinnov.jeniuscontact.databinding.ActivityDetailBinding
import com.rezkyatinnov.jeniuscontact.injection.ViewModelFactory
import com.rezkyatinnov.jeniuscontact.ui.BaseActivity
import javax.inject.Inject

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class DetailActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: DetailViewModel

    var idContact = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        getIntentData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadContactDetail(idContact)
    }

    private fun getIntentData() {
        val extra = intent.extras
        if (extra != null) {
            idContact = extra.getString("id")?:""
        }
    }

}
