package com.cxexpert.jetpack2.api

import com.cxexpert.jetpack2.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    private fun getInitContent(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getInitInstance(): ApiHelper{
        return getInitContent().create(ApiHelper::class.java)
    }
}