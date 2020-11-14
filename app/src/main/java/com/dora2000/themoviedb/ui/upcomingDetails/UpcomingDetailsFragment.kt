package com.dora2000.themoviedb.ui.upcomingDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.UpcomingResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_upcoming_details.*

class UpcomingDetailsFragment : Fragment() {
    private val upcomingArgs : UpcomingDetailsFragmentArgs by navArgs()
    private lateinit var upcomingItem: UpcomingResultsItem
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upcomingItem = upcomingArgs.upcomingResultsItemObject
        val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        Picasso.get().load(BASE_URL+upcomingItem.posterPath).into(img_details_upcoming_poster)
        upcoming_details_name.text = upcomingItem.title
        upcoming_details_popularity.text = upcomingItem.popularity.toString()
        upcoming_details_voteCount.text = upcomingItem.voteCount.toString()
        upcoming_details_age.text = upcomingItem.adult.toString()
    }
}