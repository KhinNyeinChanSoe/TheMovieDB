package com.dora2000.themoviedb.ui.popularDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.PopularResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_now_playing_details.*
import kotlinx.android.synthetic.main.fragment_popular_details.*

class PopularDetailsFragment : Fragment() {

    private val popularArgs: PopularDetailsFragmentArgs by navArgs()
    private lateinit var popularItems:PopularResultsItem
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularItems = popularArgs.popularResultsItemObject
        val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        Picasso.get().load(BASE_URL+popularItems.posterPath).into(img_details_popular_poster)
        popular_details_name.text = popularItems.title
        popular_details_popularity.text = popularItems.popularity.toString()
        popular_details_voteCount.text = popularItems.voteCount.toString()
        popular_details_age.text = popularItems.adult.toString()
//        Log.d("TEST_DATA>>>",popularItems.title.toString())
    }


}