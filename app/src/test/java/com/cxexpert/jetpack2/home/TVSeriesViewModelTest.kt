package com.cxexpert.jetpack2.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cxexpert.jetpack2.data.model.SeriesModel
import com.cxexpert.jetpack2.data.repository.Repository
import com.cxexpert.jetpack2.ui.home.viewmodel.TVSeriesViewModel
import com.cxexpert.jetpack2.utils.DummyData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockitokotlin2.verify

@RunWith(MockitoJUnitRunner::class)
class TVSeriesViewModelTest {
    private lateinit var tvVM: TVSeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<SeriesModel.Results>>

    @Before
    fun setUp(){
        tvVM = TVSeriesViewModel(repository)
    }

    @Test
    fun testTVSeries(){
        val tv = MutableLiveData<List<SeriesModel.Results>>()
        val dummy = DummyData.getDummyTVSeries()
        tv.value = dummy

        `when`(repository.getTVSeries()).thenReturn(tv)
        tvVM.getTVSeries()
        val data = tvVM.data
        verify(repository).getTVSeries()

        assertNotNull(data)
        assertEquals(21, data.value?.size)

        tvVM.data.observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}