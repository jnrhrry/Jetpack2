package com.cxexpert.jetpack2.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.data.repository.Repository

class MovieViewModel(private val repository: Repository): ViewModel(){
    lateinit var data: LiveData<List<MovieModel.Results>>
    fun getMovie(){
        data = repository.getMovies()
    }
}