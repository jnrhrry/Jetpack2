package com.cxexpert.jetpack2.data.model

class DetailModel {
    data class Movie(
        val id: Int? = null,
        val title: String? = null,
        val overview: String? = null,
        val poster_path: String? = null,
        val release_date: String? = null,
        val vote_average: Double? = null
    )

    data class TvSeries(
        val id: Int? = null,
        val name: String? = null,
        val overview: String? = null,
        val poster_path: String? = null,
        val first_air_date: String? = null,
        val vote_average: Double? = null
    )
}