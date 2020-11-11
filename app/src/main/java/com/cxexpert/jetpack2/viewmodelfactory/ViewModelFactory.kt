package com.cxexpert.jetpack2.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cxexpert.jetpack2.data.repository.Repository
import com.cxexpert.jetpack2.ui.detail.movie.MovieDetailViewModel
import com.cxexpert.jetpack2.ui.detail.tvseries.TVSeriesDetailViewModel
import com.cxexpert.jetpack2.ui.home.viewmodel.MovieViewModel
import com.cxexpert.jetpack2.ui.home.viewmodel.TVSeriesViewModel
import com.cxexpert.jetpack2.utils.di.Injection

class ViewModelFactory private constructor(private val repository: Repository): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }
    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TVSeriesViewModel::class.java) -> {
                TVSeriesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TVSeriesDetailViewModel::class.java) -> {
                TVSeriesDetailViewModel(repository) as T
            }
            else -> throw Throwable("Unknown VewModel Class:" + modelClass.name)
        }
    }
}