package com.dora2000.themoviedb.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.SearchResultsItem
import kotlinx.android.synthetic.main.fragment_nowplaying.*
import kotlinx.android.synthetic.main.fragment_search.*
import java.nio.file.Files.find


class SearchFragment : Fragment() {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchView: SearchView
    private lateinit var keyword: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchAdapter = SearchAdapter()
//        searchViewModel.loadSearchResult()
        setupSearchRecyclerView()
        searchViewModel.getSearchArticle().observe(viewLifecycleOwner, Observer {
            searchAdapter.setWord(it.search_results as List<SearchResultsItem>)
        })
        searchViewModel.getSearchErrorStatus().observe(viewLifecycleOwner, Observer { status ->
            if (status) {
                searchErrorMessage.visibility = View.VISIBLE
                searchViewModel.getSearchErrorMessage().observe(viewLifecycleOwner, Observer {
                    searchErrorMessage.text = it
                })
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.action_search)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            searchView.queryHint = "Enter Movie Name"
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    var searchKeyword = "%$newText%"
                    searchViewModel.getSearchResult(searchKeyword).observe(viewLifecycleOwner,
                        Observer { searchAdapter.setWord(it.search_results as List<SearchResultsItem>) })
                    setupSearchRecyclerView()
                    searchViewModel.loadSearchResult(searchKeyword)
                    keyword = searchKeyword
                    return true
                }

            })
        }
    }

    override fun onResume() {
        super.onResume()
//        searchViewModel.loadSearchResult(keyword)
    }

    fun setupSearchRecyclerView() {
        recycler_search.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = searchAdapter
        }
    }
}