package com.rezkyatinnov.jeniuscontact.restapi

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


@Module
class RestapiModule(val context: Context) {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .readTimeout((2 * 60).toLong(), TimeUnit.SECONDS)
            .connectTimeout((2 * 60).toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiUrl.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    internal fun getApiServices(retrofit: Retrofit): ApiServices {
        return retrofit.create<ApiServices>(ApiServices::class.java)
    }

    @Provides
    @Singleton
    internal fun getRequestHeader(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .build()
            chain.proceed(request)
        }
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)

        return httpClient.build()
    }
}
