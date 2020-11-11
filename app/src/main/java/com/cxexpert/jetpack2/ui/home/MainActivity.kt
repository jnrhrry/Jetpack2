package com.cxexpert.jetpack2.ui.home

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cxexpert.jetpack2.R
import com.cxexpert.jetpack2.ui.adapter.MovieAdapter
import com.cxexpert.jetpack2.ui.adapter.TVSeriesAdapter
import com.cxexpert.jetpack2.ui.home.viewmodel.MovieViewModel
import com.cxexpert.jetpack2.ui.home.viewmodel.TVSeriesViewModel
import com.cxexpert.jetpack2.utils.EspressoIdlingResource
import com.cxexpert.jetpack2.utils.gone
import com.cxexpert.jetpack2.utils.show
import com.cxexpert.jetpack2.viewmodelfactory.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val factory = ViewModelFactory.getInstance(this)
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var seriesAdapter: TVSeriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainMovie()
        mainTVSeries()

        val iconJetPack = findViewById<ImageView>(R.id.popcorn)
        iconJetPack.setOnLongClickListener {
            Snackbar.make(it, "This app is second submission for Belajar Android Jetpack Pro course at Dicoding.", Snackbar.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }
    private fun mainMovie(){
        val movieVM = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        movie_progressbar.show()
        movieVM.getMovie()
        EspressoIdlingResource.increment()
        movieVM.data.observe(this, Observer {
            if(it != null){
                movie_progressbar.gone()
                movieAdapter = MovieAdapter(this, it)
                movieAdapter.notifyDataSetChanged()
                rv_movie.adapter = movieAdapter
                rv_movie.setHasFixedSize(true)
                rv_movie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            } else {
                movie_progressbar.gone()
            }
            EspressoIdlingResource.decrement()
        })
    }

    private fun mainTVSeries(){
        val seriesVM = ViewModelProvider(this, factory)[TVSeriesViewModel::class.java]
        series_progressbar.show()
        seriesVM.getTVSeries()
        EspressoIdlingResource.increment()
        seriesVM.data.observe(this, Observer {
            if(it != null){
                series_progressbar.gone()
                seriesAdapter = TVSeriesAdapter(this, it)
                seriesAdapter.notifyDataSetChanged()
                rv_series.setHasFixedSize(true)
                rv_series.adapter = seriesAdapter
                rv_series.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            } else {
                series_progressbar.gone()
            }
            EspressoIdlingResource.decrement()
        })
    }
}