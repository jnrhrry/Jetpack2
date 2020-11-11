package com.cxexpert.jetpack2.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cxexpert.jetpack2.data.model.DetailModel
import com.cxexpert.jetpack2.data.repository.Repository
import com.cxexpert.jetpack2.ui.detail.movie.MovieDetailViewModel
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
class MovieDetailViewModelTest {
    private lateinit var movieDetailVM: MovieDetailViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<DetailModel.Movie>

    @Before
    fun setUp(){
        movieDetailVM = MovieDetailViewModel(repository)
    }

    @Test
    fun movieDetailTest(){
        val dummy = DummyData.getDummyMovieDetail()
        val id = dummy.id
        val movie = MutableLiveData<DetailModel.Movie>()
        movie.value = dummy

        `when`(repository.getMovieDetails(id)).thenReturn(movie)
        val data = movieDetailVM.getMovieDetails(id).value
        verify(repository).getMovieDetails(id)

        assertNotNull(data)
        assertEquals(data?.id, dummy.id)
        assertEquals(data?.poster_path, dummy.poster_path)
        assertEquals(data?.title, dummy.title)
        assertEquals(data?.overview, dummy.overview)
        assertEquals(data?.release_date, dummy.release_date)
        assertEquals(data?.vote_average, dummy.vote_average)
        movieDetailVM.getMovieDetails(id).observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}