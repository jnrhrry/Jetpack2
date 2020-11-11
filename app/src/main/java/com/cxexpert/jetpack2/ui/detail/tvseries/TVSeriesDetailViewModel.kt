package com.cxexpert.jetpack2.ui.detail.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.repository.Repository

class TVSeriesDetailViewModel (private val repository: Repository): ViewModel(){
    fun getTVSeriesDetails(id: Int?): LiveData<DetailModel.TvSeries> = repository.getTVSeriesDetails(id)
}