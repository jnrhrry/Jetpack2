package com.cxexpert.jetpack2.utils.di

import android.content.Context
import com.cxexpert.jetpack2.api.JsonHelper
import com.cxexpert.jetpack2.data.remote.RemoteDataSource
import com.cxexpert.jetpack2.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository{
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return Repository.getInstance(remoteDataSource)
    }
}