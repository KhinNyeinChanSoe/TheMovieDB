package com.dora2000.themoviedb.ui.top_rated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.PopularResultsItem
import com.dora2000.themoviedb.model.TopRatedResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_details.view.*

class TopRatedAdapter : RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {
    var clickListener_top_rated:ClickListener ?= null
    private var topRatedResultsItemList: List<TopRatedResultsItem> = ArrayList()

    inner class TopRatedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var topRatedItem: TopRatedResultsItem
        val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        fun bind(resultsItem: TopRatedResultsItem) {
            this.topRatedItem = resultsItem
            itemView.txt_movie_name.text = resultsItem.title
            Picasso.get().load(BASE_URL + resultsItem.posterPath).into(itemView.img_poster)
        }

        override fun onClick(v: View?) {
            clickListener_top_rated?.onClick(topRatedItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_details, parent, false)
        return TopRatedViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(topRatedResultsItemList[position])
    }

    override fun getItemCount(): Int = topRatedResultsItemList.size
    fun updateTopRatedItemList(topRatedResultsItem: List<TopRatedResultsItem>){
        this.topRatedResultsItemList = topRatedResultsItem
        notifyDataSetChanged()
    }
    interface ClickListener{
        fun onClick(popular_item: TopRatedResultsItem)
    }
    fun setOnClickListener(clickListener:ClickListener){
        this.clickListener_top_rated = clickListener
    }


}