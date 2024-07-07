package com.itis.filmy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.itis.filmy.databinding.FragmentWatchedBinding
import java.util.stream.Collectors

class WatchedFragment: Fragment(R.layout.fragment_watched) {
    private var binding: FragmentWatchedBinding?= null
    private var adapter: FilmAdapter?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWatchedBinding.bind(view)
        initAdapter()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    private fun initAdapter(){

        binding?.run {
            adapter = FilmAdapter(
                FilmsRepository.films.stream().filter { x -> x.type == "watched" }.collect(Collectors.toList()),
                Glide.with(this@WatchedFragment),
                onClick = {
                    val id = it.id
                    val bundle = Bundle()
                    bundle.putString("ARG_TEXT", id.toString())
                    //findNavController().navigate(R.id.action_citiesFragment_to_cityFragment, bundle)
                }
            )
            rvWatched.adapter = adapter
            rvWatched.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}