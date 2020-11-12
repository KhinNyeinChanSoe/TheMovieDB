package com.dora2000.themoviedb.ui.nowPlayingDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.NowPlayingResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_now_playing_details.*

class NowPlayingDetailsFragment : Fragment() {
    private val nowplayingArgs : NowPlayingDetailsFragmentArgs by navArgs()
    private lateinit var nowPlayingItem:NowPlayingResultsItem
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingItem = nowplayingArgs.nowplayingResultsItemObject
        val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        Picasso.get().load(BASE_URL+nowPlayingItem.posterPath).into(img_details_nowplaying_poster)
        nowplaying_details_name.text = nowPlayingItem.title
        nowplaying_details_popularity.text = nowPlayingItem.popularity.toString()
        nowplaying_details_voteCount.text = nowPlayingItem.voteCount.toString()
        nowplaying_details_age.text = nowPlayingItem.adult.toString()
    }
}