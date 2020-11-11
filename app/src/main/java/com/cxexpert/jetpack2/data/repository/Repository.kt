package com.cxexpert.jetpack2.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.data.model.SeriesModel
import com.cxexpert.jetpack2.data.remote.RemoteDataSource
import com.cxexpert.jetpack2.data.source.DataSource

class Repository private constructor(private val remoteDataSource: RemoteDataSource): DataSource{
    companion object{
        @Volatile
        private var instance: Repository?=null
        fun getInstance(remoteDataSource: RemoteDataSource): Repository = instance ?: synchronized(this){
            instance ?: Repository(remoteDataSource)
        }
    }

    override fun getMovies(): LiveData<List<MovieModel.Results>> {
        var movie = MutableLiveData<List<MovieModel.Results>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMovie{
            override fun onMovie(movieResponse: MutableLiveData<List<MovieModel.Results>>) {
                movie = movieResponse
            }
        })
        return movie
    }

    override fun getTVSeries(): LiveData<List<SeriesModel.Results>> {
        var series = MutableLiveData<List<SeriesModel.Results>>()
        remoteDataSource.getTVSeries(object : RemoteDataSource.LoadTVSeries{
            override fun onTVSeries(seriesResponse: MutableLiveData<List<SeriesModel.Results>>) {
                series = seriesResponse
            }
        })
        return series
    }

    override fun getMovieDetails(id: Int?): LiveData<DetailModel.Movie> {
        var movieDetail = MutableLiveData<DetailModel.Movie>()
        remoteDataSource.getMovieDetails(id, object : RemoteDataSource.LoadMovieDetail{
            override fun onMovieDetail(movieDetailResponse: MutableLiveData<DetailModel.Movie>) {
                movieDetail = movieDetailResponse
            }
        })
        return movieDetail
    }

    override fun getTVSeriesDetails(id: Int?): LiveData<DetailModel.TvSeries> {
        var seriesDetail = MutableLiveData<DetailModel.TvSeries>()
        remoteDataSource.getTVSeriesDetail(id, object : RemoteDataSource.LoadTVSeriesDetail{
            override fun onTVSeriesDetail(seriesDetailResponse: MutableLiveData<DetailModel.TvSeries>) {
                seriesDetail = seriesDetailResponse
            }
        })
        return seriesDetail
    }
}