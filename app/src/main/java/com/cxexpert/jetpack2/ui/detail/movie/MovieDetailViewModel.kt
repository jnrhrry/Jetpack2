package com.cxexpert.jetpack2.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.repository.Repository

class MovieDetailViewModel (private val repository: Repository): ViewModel(){
    fun getMovieDetails(id: Int?): LiveData<DetailModel.Movie> = repository.getMovieDetails(id)
}