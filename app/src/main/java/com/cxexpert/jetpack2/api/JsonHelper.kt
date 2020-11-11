package com.cxexpert.jetpack2.api

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.data.model.SeriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper (private val context: Context){
    fun loadMovies(): MutableLiveData<List<MovieModel.Results>>{
        val list = MutableLiveData<List<MovieModel.Results>>()
        val config = ApiConfig().getInitInstance()
        config.getMovie().enqueue(object: Callback<MovieModel.MovieResponse>{
            override fun onResponse(
                call: Call<MovieModel.MovieResponse>,
                response: Response<MovieModel.MovieResponse>
            ) {
                Log.e("MovieViewModel", response.message())
                val body = response.body()?.results
                list.postValue(body)
            }
            override fun onFailure(call: Call<MovieModel.MovieResponse>, t: Throwable) {
                Log.e("MovieViewModel", t.toString())
            }
        })
        return list
    }
    fun loadTVSeries(): MutableLiveData<List<SeriesModel.Results>>{
        val list = MutableLiveData<List<SeriesModel.Results>>()
        val config = ApiConfig().getInitInstance()
        config.getSeries().enqueue(object : Callback<SeriesModel.SeriesResponse> {
            override fun onFailure(call: Call<SeriesModel.SeriesResponse>, t: Throwable) {
                Log.e("TvViewModel", t.toString())
            }
            override fun onResponse(
                call: Call<SeriesModel.SeriesResponse>,
                response: Response<SeriesModel.SeriesResponse>
            ) {
                Log.e("TvViewModel", response.toString())
                val body = response.body()?.results
                list.postValue(body)
            }
        })
        return list
    }
    fun loadDetailMovie(id: Int?): MutableLiveData<DetailModel.Movie> {
        val data = MutableLiveData<DetailModel.Movie>()
        val call = ApiConfig().getInitInstance()
        call.getDetailMovie(id).enqueue(object : Callback<DetailModel.Movie>{
            override fun onFailure(call: Call<DetailModel.Movie>, t: Throwable) {
                Log.e("DetailMovieViewModel", t.toString())

            }
            override fun onResponse(call: Call<DetailModel.Movie>, response: Response<DetailModel.Movie>) {
                val body = response.body()
                Log.e("DetailMovieViewModel", body.toString())
                data.postValue(body)
            }
        })
        return data
    }

    fun loadDetailTvSeries(id: Int?): MutableLiveData<DetailModel.TvSeries> {
        val dataTv = MutableLiveData<DetailModel.TvSeries>()
        val call = ApiConfig().getInitInstance()
        call.getDetailTv(id).enqueue(object : Callback<DetailModel.TvSeries>{
            override fun onFailure(call: Call<DetailModel.TvSeries>, t: Throwable) {
                Log.e("DetailTvViewModel", t.toString())
            }
            override fun onResponse(call: Call<DetailModel.TvSeries>, response: Response<DetailModel.TvSeries>) {
                val body = response.body()
                Log.e("DetailTvViewModel", body.toString())
                dataTv.postValue(body)
            }
        })
        return dataTv
    }
}