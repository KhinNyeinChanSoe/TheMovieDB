package com.dora2000.themoviedb.ui.upcoming


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dora2000.themoviedb.R
import com.dora2000.themoviedb.model.UpcomingResultsItem
import kotlinx.android.synthetic.main.fragment_upcoming.*

class UpcomingFragment : Fragment(),UpcomingAdapter.ClickListener_Upcoming {

    private lateinit var upcomingViewModel: UpcomingViewModel
    private lateinit var upcomingAdapter: UpcomingAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upcomingViewModel = ViewModelProvider(this).get(UpcomingViewModel::class.java)
        upcomingAdapter = UpcomingAdapter()
        upcomingViewModel.loadResult()
        upcomingAdapter.setOnClickListener(this)
        setupRecyclerView()
        observeViewModel()
        swipeRefreshLayout.setOnRefreshListener {
            observeViewModel()
            setupRecyclerView()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel() {
        upcomingViewModel.getResult().observe(
            viewLifecycleOwner,
            Observer { upcomingAdapter.updateResultsItem(it.results as List<UpcomingResultsItem>) })
        upcomingViewModel.getIsLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            loadingProgress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        })
        upcomingViewModel.getErrorStatus().observe(viewLifecycleOwner, Observer { status ->
            if (status) {
                errorMessage.visibility = View.VISIBLE
                upcomingViewModel.getErrorMessage()
                    .observe(viewLifecycleOwner, Observer { errorMessage.text = it })
            }
        })
    }

    fun setupRecyclerView() {
        recycler_upcoming.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = upcomingAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        upcomingViewModel.loadResult()
    }

    override fun onClick(up_coming_item: UpcomingResultsItem) {
        val nowPlayingDirection = UpcomingFragmentDirections.actionNavUpcomingToUpcomingDetailsFragment(up_coming_item)
        findNavController().navigate(nowPlayingDirection)
    }



}
