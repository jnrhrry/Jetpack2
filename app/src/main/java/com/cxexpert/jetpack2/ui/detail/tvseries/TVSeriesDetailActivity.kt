package com.cxexpert.jetpack2.ui.detail.tvseries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cxexpert.jetpack2.BuildConfig
import com.cxexpert.jetpack2.R
import com.cxexpert.jetpack2.data.model.SeriesModel
import com.cxexpert.jetpack2.viewmodelfactory.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_tvseries.*

class TVSeriesDetailActivity : AppCompatActivity() {
    var series: SeriesModel.Results?=null
    companion object {
        const val EXTRA_TV_SERIES = "extra_tv_series"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvseries)

        setSupportActionBar(content_detail_name_tv_series)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        series = intent.getParcelableExtra(EXTRA_TV_SERIES)
        content_detail_name_tv_series.title = series?.name
        val factory = ViewModelFactory.getInstance(this)
        val detailTVSeriesVM = ViewModelProvider(this, factory)[TVSeriesDetailViewModel::class.java]
        detailTVSeriesVM.getTVSeriesDetails(series?.id).observe(this, Observer {
            content_rating_tv_series.text = it.vote_average.toString()
            content_release_date_tv_series.text = it.first_air_date
            content_overview_tv_series.text = it.overview
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL+it.poster_path)
                .into(detail_image_tv_series)
        })

        val playButton = findViewById<FloatingActionButton>(R.id.play_button_series)
        playButton.setOnClickListener {
            Snackbar.make(it, "Features to play episodes of ${series?.name} will be available soon.", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}