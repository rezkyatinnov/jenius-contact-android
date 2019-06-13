package com.rezkyatinnov.jeniuscontact

import android.app.Application
import android.content.Context

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


class JeniusContactApp: Application() {
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}