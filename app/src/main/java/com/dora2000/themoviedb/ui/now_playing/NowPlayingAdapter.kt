package com.dora2000.themoviedb.ui.now_playing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.NowPlayingResultsItem

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_details.view.*

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {
    private var resultsItemsList: List<NowPlayingResultsItem> = ArrayList()
    var clickListener_nowplaying:ClickListener_NowPlaying?= null
    inner class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var nowplayingItem:NowPlayingResultsItem
        val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        fun bind(resultsItem: NowPlayingResultsItem) {
            this.nowplayingItem = resultsItem
            itemView.txt_movie_name.text = resultsItem.originalTitle
            Picasso.get().load(BASE_URL+ resultsItem.posterPath).into(itemView.img_poster)
        }

    override fun onClick(v: View?) {
        clickListener_nowplaying?.onClick(nowplayingItem)
    }

}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_details, parent, false)
        return NowPlayingViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(resultsItemsList[position])
    }

    override fun getItemCount(): Int = resultsItemsList.size

    fun updateResultsItem(resultsItem: List<NowPlayingResultsItem>) {
        this.resultsItemsList = resultsItem
        notifyDataSetChanged()
    }
    interface ClickListener_NowPlaying{
        fun onClick(nowPlayingResultsItem: NowPlayingResultsItem)
    }
    fun setOnClickListener(clickListener:ClickListener_NowPlaying){
        this.clickListener_nowplaying = clickListener
    }
}
