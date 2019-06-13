package com.rezkyatinnov.jeniuscontact.ui.createupdatecontact

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rezkyatinnov.jeniuscontact.R
import com.rezkyatinnov.jeniuscontact.databinding.ActivityAddContactBinding
import com.rezkyatinnov.jeniuscontact.injection.ViewModelFactory
import com.rezkyatinnov.jeniuscontact.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_add_contact.*
import javax.inject.Inject as Inject1

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class CreateUpdateContactActivity : BaseActivity() {
    @Inject1
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: ActivityAddContactBinding
    lateinit var viewModel: CreateUpdateContactViewModel

    var isUpdate = false
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_contact)
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateUpdateContactViewModel::class.java)
        binding.viewModel = viewModel

        initData()

    }

    fun initData(){
        if (isUpdate) {
            supportActionBar!!.title = getString(R.string.editcontact)
            viewModel.preloadContactData(id)
        } else {
            supportActionBar!!.title = getString(R.string.addcontact)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getIntentData() {
        val extra = intent.extras
        if (extra != null) {
            isUpdate = extra.getBoolean("isUpdate",false)
            id = extra.getString("id")?:""
        }
    }
}
