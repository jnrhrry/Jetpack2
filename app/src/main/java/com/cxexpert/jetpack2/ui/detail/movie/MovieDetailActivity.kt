package com.cxexpert.jetpack2.ui.detail.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cxexpert.jetpack2.BuildConfig
import com.cxexpert.jetpack2.R
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.viewmodelfactory.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_movie.*

class MovieDetailActivity : AppCompatActivity() {
    var movie: MovieModel.Results?=null
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(content_detail_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        movie = intent.getParcelableExtra(EXTRA_MOVIE)

        val factory = ViewModelFactory.getInstance(this)
        val detailMovieVM = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]
        content_detail_name.title = movie?.original_title
        detailMovieVM.getMovieDetails(movie?.id).observe(this, Observer {
            content_rating.text = it.vote_average.toString()
            content_release_date.text = it.release_date
            content_overview.text = it.overview
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL+it.poster_path)
                .into(detail_image)
        })

        val playButton = findViewById<FloatingActionButton>(R.id.play_button)
        playButton.setOnClickListener {
            Snackbar.make(it, "Feature to play ${movie?.original_title} movie will be available soon.", Snackbar.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}