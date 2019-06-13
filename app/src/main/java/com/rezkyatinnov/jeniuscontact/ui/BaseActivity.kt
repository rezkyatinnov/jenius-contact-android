package com.rezkyatinnov.jeniuscontact.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.rezkyatinnov.jeniuscontact.injection.AppComponent
import com.rezkyatinnov.jeniuscontact.injection.AppModule
import com.rezkyatinnov.jeniuscontact.injection.DaggerAppComponent
import com.rezkyatinnov.jeniuscontact.restapi.RestapiModule
import com.rezkyatinnov.jeniuscontact.ui.addcontact.AddContactActivity
import com.rezkyatinnov.jeniuscontact.ui.detail.DetailActivity
import com.rezkyatinnov.jeniuscontact.ui.main.MainActivity

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


open class BaseActivity : AppCompatActivity() {
    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent =  DaggerAppComponent.builder().appModule(AppModule(this)).restapiModule(RestapiModule(this)).build()
        inject()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        appComponent =  DaggerAppComponent.builder().appModule(AppModule(this)).restapiModule(RestapiModule(this)).build()
        inject()
    }

    private fun inject() {
        when (this) {
            is MainActivity -> appComponent.inject(this)
            is DetailActivity -> appComponent.inject(this)
            is AddContactActivity -> appComponent.inject(this)
        }
    }
}
