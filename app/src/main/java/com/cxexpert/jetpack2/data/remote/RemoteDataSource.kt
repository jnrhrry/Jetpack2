package com.cxexpert.jetpack2.data.remote

import androidx.lifecycle.MutableLiveData
import com.cxexpert.jetpack2.api.JsonHelper
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.data.model.SeriesModel

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){
    companion object{
        @Volatile
        private var instance: RemoteDataSource?=null
        fun getInstance(helper: JsonHelper): RemoteDataSource = instance ?: synchronized(this){
            instance ?: RemoteDataSource(helper)
        }
    }

    interface LoadMovie{
        fun onMovie(movieResponse: MutableLiveData<List<MovieModel.Results>>)
    }
    interface LoadTVSeries{
        fun onTVSeries(seriesResponse: MutableLiveData<List<SeriesModel.Results>>)
    }
    interface LoadMovieDetail{
        fun onMovieDetail(movieDetailResponse: MutableLiveData<DetailModel.Movie>)
    }
    interface LoadTVSeriesDetail{
        fun onTVSeriesDetail(seriesDetailResponse: MutableLiveData<DetailModel.TvSeries>)
    }

    fun getMovies(callback: LoadMovie){
        callback.onMovie(jsonHelper.loadMovies())
    }
    fun getTVSeries(callback: LoadTVSeries){
        callback.onTVSeries(jsonHelper.loadTVSeries())
    }
    fun getMovieDetails(id: Int?, callback: LoadMovieDetail){
        callback.onMovieDetail(jsonHelper.loadDetailMovie(id))
    }
    fun getTVSeriesDetail(id: Int?, callback: LoadTVSeriesDetail){
        callback.onTVSeriesDetail(jsonHelper.loadDetailTvSeries(id))
    }
}