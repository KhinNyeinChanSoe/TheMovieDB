package com.dora2000.themoviedb.ui.now_playing


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.NowPlayingResultsItem
import com.dora2000.themoviedb.ui.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_nowplaying.*

class NowPlayingFragment : Fragment(),NowPlayingAdapter.ClickListener_NowPlaying {

    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var nowPlayingAdapter: NowPlayingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nowplaying, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        nowPlayingAdapter = NowPlayingAdapter()
        nowPlayingViewModel.loadResult()
        nowPlayingAdapter.setOnClickListener(this)
        setupRecyclerView()
        observeViewModel()
        swipeRefreshLayout.setOnRefreshListener {
            observeViewModel()
            setupRecyclerView()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel() {
        nowPlayingViewModel.getResult().observe(
            viewLifecycleOwner,
            Observer { nowPlayingAdapter.updateResultsItem(it.results as List<NowPlayingResultsItem>) })
        nowPlayingViewModel.getIsLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            loadingProgress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        })
        nowPlayingViewModel.getErrorStatus().observe(viewLifecycleOwner, Observer { status ->
            if (status) {
                errorMessage.visibility = View.VISIBLE
                nowPlayingViewModel.getErrorMessage()
                    .observe(viewLifecycleOwner, Observer { errorMessage.text = it })
            }
        })
    }

    fun setupRecyclerView() {
        recycler_nowPlaying.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = nowPlayingAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        nowPlayingViewModel.loadResult()
    }

    override fun onClick(nowPlayingResultsItem: NowPlayingResultsItem) {
        val nowPlayingDirection = NowPlayingFragmentDirections.actionNavNowPlayingToNowPlayingDetailsFragment(nowPlayingResultsItem)
        findNavController().navigate(nowPlayingDirection)
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.main,menu)
//        val searchItem = menu.findItem(R.id.action_search)
//        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        if (searchItem!= null){
//            nowplaying_searchView = searchItem.actionView as SearchView
//
//        }
//        if (nowplaying_searchView!=null){
//            nowplaying_searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
//            nowplaying_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    return false
//                }
//
//            })
//        }
//    }

}
