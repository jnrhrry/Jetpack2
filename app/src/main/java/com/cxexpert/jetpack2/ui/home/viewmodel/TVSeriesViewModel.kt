package com.cxexpert.jetpack2.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cxexpert.jetpack2.data.model.SeriesModel
import com.cxexpert.jetpack2.data.repository.Repository

class TVSeriesViewModel (private val repository: Repository): ViewModel(){
    lateinit var data: LiveData<List<SeriesModel.Results>>

    fun getTVSeries(){
        data = repository.getTVSeries()
    }
}