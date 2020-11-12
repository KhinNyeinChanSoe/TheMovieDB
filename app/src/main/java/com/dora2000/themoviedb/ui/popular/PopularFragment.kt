package com.dora2000.themoviedb.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.PopularResultsItem
import kotlinx.android.synthetic.main.fragment_nowplaying.*
import kotlinx.android.synthetic.main.fragment_nowplaying.errorMessage
import kotlinx.android.synthetic.main.fragment_nowplaying.loadingProgress
import kotlinx.android.synthetic.main.fragment_nowplaying.swipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment(),PopularAdapter.ClickListener {

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var popularAdapter: PopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        var root = inflater.inflate(R.layout.fragment_popular,container,false)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        popularAdapter = PopularAdapter()
        popularViewModel.loadPopularResult()
        popularAdapter.setOnClickListener(this)
        setupPopularRecycler()
        popular_observe_viewmodel()
        swipeRefreshLayout_popular.setOnRefreshListener {
            popular_observe_viewmodel()
            setupPopularRecycler()
            swipeRefreshLayout_popular.isRefreshing = false
        }



    }

    fun setupPopularRecycler() {
        recycler_popular.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = popularAdapter
        }
    }

    fun popular_observe_viewmodel() {
        popularViewModel.getPopularResult().observe(viewLifecycleOwner, Observer {
            popularAdapter.updatePopularItemList(it.popular_results as List<PopularResultsItem>)
        })
        popularViewModel.getPopularIsLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            popularLoadingProgress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        })
        popularViewModel.getPopularErrorStatus().observe(viewLifecycleOwner, Observer { status ->
            if (status) {
                popularErrorMessage.visibility = View.VISIBLE
                popularViewModel.getPopularErrorMessage()
                    .observe(viewLifecycleOwner, Observer { popularErrorMessage.text = it })
            }
        })
    }

    override fun onResume() {
        super.onResume()
        popularViewModel.loadPopularResult()
    }

    override fun onClick(popular_item: PopularResultsItem) {
        val popularDirections = PopularFragmentDirections.actionNavPopularToPopularDetailsFragment(popular_item)
        findNavController().navigate(popularDirections)
    }


}