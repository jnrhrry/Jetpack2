package com.cxexpert.jetpack2.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class MovieModel {

    data class MovieResponse (
        val results: ArrayList<Results>
    )

    @Parcelize
    data class Results (
        val id: Int = 0,
        val poster_path: String? = null,
        val original_title: String? = null,
        val vote_average: Double = 0.0
    ): Parcelable
}