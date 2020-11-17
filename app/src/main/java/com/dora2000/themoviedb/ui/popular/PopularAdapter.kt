package com.dora2000.themoviedb.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.PopularResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_details.view.*

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    var clickListener_popular:ClickListener ?= null
    private var popularResultsItemList: List<PopularResultsItem> = ArrayList()

   inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
       lateinit var popularItem: PopularResultsItem
        private val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        fun bind(resultsItem: PopularResultsItem) {
            this.popularItem = resultsItem
            itemView.txt_movie_name.text = resultsItem.title
            Picasso.get().load(BASE_URL + resultsItem.posterPath).into(itemView.img_poster)
        }

       override fun onClick(v: View?) {
           clickListener_popular?.onClick(popularItem)
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_details, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(popularResultsItemList[position])
    }

    override fun getItemCount(): Int = popularResultsItemList.size
    fun updatePopularItemList(popularResultsItem: List<PopularResultsItem>){
        this.popularResultsItemList = popularResultsItem
        notifyDataSetChanged()
    }
    interface ClickListener{
        fun onClick(popular_item: PopularResultsItem)
    }
    fun setOnClickListener(clickListener:ClickListener){
        this.clickListener_popular = clickListener
    }


}