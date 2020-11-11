package com.cxexpert.jetpack2.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.repository.Repository
import com.cxexpert.jetpack2.ui.detail.tvseries.TVSeriesDetailViewModel
import com.cxexpert.jetpack2.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVSeriesDetailViewModelTest {

    private lateinit var seriesVM: TVSeriesDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<DetailModel.TvSeries>

    @Before
    fun setUp(){
        seriesVM = TVSeriesDetailViewModel(repository)
    }

    @Test
    fun tVSeriesDetailTest(){
        val dummy = DummyData.getDummyTVSeriesDetail()[0]
        val id = dummy.id
        val series = MutableLiveData<DetailModel.TvSeries>()
        series.value = dummy

        `when`(repository.getTVSeriesDetails(id)).thenReturn(series)
        val data = seriesVM.getTVSeriesDetails(id).value
        verify(repository).getTVSeriesDetails(id)

        assertNotNull(data)
        assertEquals(data?.id, id)
        assertEquals(data?.name, dummy.name)
        assertEquals(data?.overview, dummy.overview)
        assertEquals(data?.poster_path, dummy.poster_path)
        assertEquals(data?.first_air_date, dummy.first_air_date)
        assertEquals(data?.vote_average, dummy.vote_average)

        seriesVM.getTVSeriesDetails(id).observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}