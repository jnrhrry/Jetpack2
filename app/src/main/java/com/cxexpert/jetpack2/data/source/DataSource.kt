package com.cxexpert.jetpack2.data.source

import androidx.lifecycle.LiveData
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.data.model.SeriesModel

interface DataSource {
    fun getMovies(): LiveData<List<MovieModel.Results>>
    fun getTVSeries(): LiveData<List<SeriesModel.Results>>
    fun getMovieDetails(id: Int?): LiveData<DetailModel.Movie>
    fun getTVSeriesDetails(id: Int?): LiveData<DetailModel.TvSeries>
}