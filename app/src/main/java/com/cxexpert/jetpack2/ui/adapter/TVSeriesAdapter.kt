package com.cxexpert.jetpack2.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cxexpert.jetpack2.BuildConfig
import com.cxexpert.jetpack2.R
import com.cxexpert.jetpack2.data.model.SeriesModel
import com.cxexpert.jetpack2.ui.detail.tvseries.TVSeriesDetailActivity
import com.cxexpert.jetpack2.utils.CustomOnClickListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_card.view.*

class TVSeriesAdapter (private val activity: Activity, private val data: List<SeriesModel.Results>):
        RecyclerView.Adapter<TVSeriesAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(series: SeriesModel.Results){
            with(itemView){
                Glide.with(this)
                    .load(BuildConfig.IMAGE_URL+series.poster_path)
                    .error(R.drawable.baseline_broken_image_white_18dp)
                    .into(main_content_image)
                main_card.setOnClickListener(CustomOnClickListener(object : CustomOnClickListener.OnItemClickCallback{
                    override fun onItemClicked(v: View) {
                        val intent = Intent(activity, TVSeriesDetailActivity::class.java)
                        intent.putExtra(TVSeriesDetailActivity.EXTRA_TV_SERIES, series)
                        activity.startActivity(intent)
                    }

                }))
                main_card.setOnLongClickListener{
                    Snackbar.make(it, "${series.name}", Snackbar.LENGTH_SHORT).show()
                    return@setOnLongClickListener true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}