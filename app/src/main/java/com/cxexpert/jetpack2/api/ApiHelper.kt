package com.cxexpert.jetpack2.api

import com.cxexpert.jetpack2.BuildConfig
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.data.model.SeriesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiHelper{
    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    fun getMovie() : Call<MovieModel.MovieResponse>

    @GET("tv/airing_today?api_key=${BuildConfig.API_KEY}")
    fun getSeries(): Call<SeriesModel.SeriesResponse>

    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
    fun getDetailMovie(
        @Path("movie_id") id: Int?
    ): Call<DetailModel.Movie>

    @GET("tv/{id}?api_key=${BuildConfig.API_KEY}")
    fun getDetailTv(
        @Path("id") id: Int?
    ): Call<DetailModel.TvSeries>
}