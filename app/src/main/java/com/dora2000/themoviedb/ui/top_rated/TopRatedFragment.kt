package com.dora2000.themoviedb.ui.top_rated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.TopRatedResultsItem
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : Fragment(),TopRatedAdapter.ClickListener {

    private lateinit var topRatedViewModel: TopRatedViewModel
    private lateinit var topRatedAdapter: TopRatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        var root = inflater.inflate(R.layout.fragment_top_rated,container,false)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        topRatedAdapter = TopRatedAdapter()
        topRatedViewModel.loadTopRatedResult()
        topRatedAdapter.setOnClickListener(this)
        setupTopRatedRecycler()
        top_rated_observe_viewmodel()
        swipeRefreshLayout_topRated.setOnRefreshListener {
            top_rated_observe_viewmodel()
            setupTopRatedRecycler()
            swipeRefreshLayout_topRated.isRefreshing = false
        }



    }

    fun setupTopRatedRecycler() {
        recycler_topRated.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = topRatedAdapter
        }
    }

    fun top_rated_observe_viewmodel() {
        topRatedViewModel.getTopRatedResult().observe(viewLifecycleOwner, Observer {
            topRatedAdapter.updateTopRatedItemList(it.topRated_results as List<TopRatedResultsItem>)
        })
        topRatedViewModel.getTopRatedIsLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            topRatedLoadingProgress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        })
        topRatedViewModel.getTopRatedErrorStatus().observe(viewLifecycleOwner, Observer { status ->
            if (status) {
                topRatedErrorMessage.visibility = View.VISIBLE
                topRatedViewModel.getTopRatedErrorMessage()
                    .observe(viewLifecycleOwner, Observer { topRatedErrorMessage.text = it })
            }
        })
    }

    override fun onResume() {
        super.onResume()
        topRatedViewModel.loadTopRatedResult()
    }

    override fun onClick(top_rated_item: TopRatedResultsItem) {
        val topRatedDirections = TopRatedFragmentDirections.actionNavTopRatedToTopRatedDetailsFragment(top_rated_item)
        findNavController().navigate(topRatedDirections)
    }


}