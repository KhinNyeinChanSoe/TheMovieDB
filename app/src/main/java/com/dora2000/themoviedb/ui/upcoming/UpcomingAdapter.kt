package com.dora2000.themoviedb.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.UpcomingResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_details.view.*

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {
    private var resultsItemsList: List<UpcomingResultsItem> = ArrayList()
    var clickListener_upcoming:ClickListener_Upcoming?= null
    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var upcomingItem:UpcomingResultsItem
        val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        fun bind(resultsItem: UpcomingResultsItem) {
            this.upcomingItem = resultsItem
            itemView.txt_movie_name.text = resultsItem.originalTitle
            Picasso.get().load(BASE_URL+ resultsItem.posterPath).into(itemView.img_poster)
        }

        override fun onClick(v: View?) {
            clickListener_upcoming?.onClick(upcomingItem)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_details, parent, false)
        return UpcomingViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(resultsItemsList[position])
    }

    override fun getItemCount(): Int = resultsItemsList.size

    fun updateResultsItem(resultsItem: List<UpcomingResultsItem>) {
        this.resultsItemsList = resultsItem
        notifyDataSetChanged()
    }
    interface ClickListener_Upcoming{
        fun onClick(upcomingResultsItem: UpcomingResultsItem)
    }
    fun setOnClickListener(clickListener:ClickListener_Upcoming){
        this.clickListener_upcoming = clickListener
    }
}
