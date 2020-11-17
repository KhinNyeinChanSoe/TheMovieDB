package com.dora2000.themoviedb.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.PopularResultsItem
import com.dora2000.themoviedb.model.SearchMovie
import com.dora2000.themoviedb.model.SearchResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_details.view.*

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var searchResultsItemList: List<SearchResultsItem> = ArrayList()
    inner class SearchViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        fun bind(searchResultsItem: SearchResultsItem){
            itemView.txt_movie_name.text = searchResultsItem.title
            Picasso.get().load(BASE_URL+searchResultsItem.posterPath).into(itemView.img_poster)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_details,parent,false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResultsItemList[position])
    }

    override fun getItemCount(): Int = searchResultsItemList.size

//    fun updateSearchResults(searchResultItem:List<SearchResultsItem>){
//        this.searchResultsItemList = searchResultItem
//        notifyDataSetChanged()
//    }
//    private var search_movieList:MutableLiveData<SearchMovie> = MutableLiveData()
//    fun setKeyword(keyword:String):MutableLiveData<SearchMovie> = search_movieList
    fun setWord(wordList:List<SearchResultsItem>){
        this.searchResultsItemList = wordList
        notifyDataSetChanged()
    }

}