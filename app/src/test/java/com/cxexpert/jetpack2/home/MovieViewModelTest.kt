package com.cxexpert.jetpack2.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.data.repository.Repository
import com.cxexpert.jetpack2.ui.home.viewmodel.MovieViewModel
import com.cxexpert.jetpack2.utils.DummyData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{
    private lateinit var vm: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieModel.Results>>

    @Before
    fun setUp(){
        vm = MovieViewModel(repo)
    }

    @Test
    fun testMovie(){
        val movie = MutableLiveData<List<MovieModel.Results>>()
        val dummy = DummyData.getDummyMovie()
        movie.value = dummy

        `when`(repo.getMovies()).thenReturn(movie)
        vm.getMovie()
        val data = vm.data
        verify(repo).getMovies()
        assertNotNull(data)
        assertEquals(21, data.value?.size)
        vm.data.observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}