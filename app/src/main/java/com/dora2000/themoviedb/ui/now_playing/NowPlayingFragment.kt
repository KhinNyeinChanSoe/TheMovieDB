package com.dora2000.themoviedb.ui.now_playing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dora2000.themoviedb.R

class NowPlayingFragment : Fragment() {

    private lateinit var nowPlayingViewModel: NowPlayingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        nowPlayingViewModel =
                ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_nowplaying, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        nowPlayingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}