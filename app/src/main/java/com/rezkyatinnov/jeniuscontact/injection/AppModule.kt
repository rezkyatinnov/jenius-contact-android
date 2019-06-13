package com.rezkyatinnov.jeniuscontact.injection

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rezkyatinnov.jeniuscontact.JeniusContactApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


@Module
class AppModule(private val appCompatActivity: AppCompatActivity) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return appCompatActivity
    }

    @Provides
    @Singleton
    internal fun provideApp(): JeniusContactApp {
        return appCompatActivity.application as JeniusContactApp
    }

    @Provides
    @Singleton
    internal fun provideAppCompatActivity(): AppCompatActivity {
        return appCompatActivity
    }

    @Provides
    @Singleton
    internal fun getViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(appCompatActivity)
    }
}
