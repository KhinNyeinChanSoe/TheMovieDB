package com.dora2000.themoviedb.ui.topRatedDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.TopRatedResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_top_rated_details.*

class TopRatedDetailsFragment : Fragment() {

    private val topRatedArgs: TopRatedDetailsFragmentArgs by navArgs()
    private lateinit var topRatedItems:TopRatedResultsItem
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedItems = topRatedArgs.topRatedResultsItemObject
        val BASE_URL = "https://image.tmdb.org/t/p/w500/"
        Picasso.get().load(BASE_URL+topRatedItems.posterPath).into(img_details_top_rated_poster)
        top_rated_details_name.text = topRatedItems.title
        top_rated_details_popularity.text = topRatedItems.popularity.toString()
        top_rated_details_voteCount.text = topRatedItems.voteCount.toString()
        top_rated_details_age.text = topRatedItems.adult.toString()
//        Log.d("TEST_DATA>>>",popularItems.title.toString())
    }


}