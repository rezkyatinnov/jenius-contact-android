package com.rezkyatinnov.jeniuscontact.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.rezkyatinnov.jeniuscontact.R
import com.rezkyatinnov.jeniuscontact.databinding.ActivityMainBinding
import com.rezkyatinnov.jeniuscontact.injection.ViewModelFactory
import com.rezkyatinnov.jeniuscontact.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
