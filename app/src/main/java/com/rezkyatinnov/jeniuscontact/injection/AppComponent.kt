package com.rezkyatinnov.jeniuscontact.injection

import com.rezkyatinnov.jeniuscontact.restapi.RestapiModule
import com.rezkyatinnov.jeniuscontact.ui.main.MainActivity
import com.rezkyatinnov.jeniuscontact.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

@Component(modules = [AppModule::class, RestapiModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(viewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun restapiModule(restapiModule: RestapiModule): Builder
        fun appModule(appModule: AppModule): Builder
    }

}
