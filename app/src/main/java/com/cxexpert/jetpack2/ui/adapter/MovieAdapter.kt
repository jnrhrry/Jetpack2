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
import com.cxexpert.jetpack2.data.model.MovieModel
import com.cxexpert.jetpack2.ui.detail.movie.MovieDetailActivity
import com.cxexpert.jetpack2.utils.CustomOnClickListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_card.view.*


class MovieAdapter (private val activity: Activity, private val data: List<MovieModel.Results>):
        RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(movie: MovieModel.Results){
            with(itemView){
                Glide.with(this)
                    .load(BuildConfig.IMAGE_URL+movie.poster_path)
                    .error(R.drawable.baseline_broken_image_white_18dp)
                    .into(main_content_image)
                main_card.setOnClickListener(CustomOnClickListener(object :CustomOnClickListener.OnItemClickCallback{
                    override fun onItemClicked(v: View) {
                        val intent = Intent(activity, MovieDetailActivity::class.java)
                        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
                        activity.startActivity(intent)
                    }
                }))
                main_card.setOnLongClickListener{
                    Snackbar.make(it, "${movie.original_title}", Snackbar.LENGTH_SHORT).show()
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