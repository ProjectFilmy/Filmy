package com.itis.filmy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.itis.filmy.databinding.FragmentPlannedBinding
import java.util.stream.Collectors

class PlannedFragment : Fragment(R.layout.fragment_planned) {
    private var binding: FragmentPlannedBinding?= null
    private var adapter: FilmAdapter?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlannedBinding.bind(view)
        initAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initAdapter(){
        adapter = FilmAdapter(
            FilmsRepository.films.stream().filter { x -> x.type == "Planned" }.collect(Collectors.toList()),
            Glide.with(this@PlannedFragment)
        ){
                filmId -> findNavController().navigate(
                R.id.action_plannedFragment_to_filmDetailFragment,
                    bundleOf("FILM_ID" to filmId)

                )
        }

        binding?.run {
            rvPlanned.adapter = adapter
            rvPlanned.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

}