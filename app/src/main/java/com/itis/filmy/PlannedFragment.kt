package com.itis.filmy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
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

        binding?.run {
            adapter = FilmAdapter(
                FilmsRepository.films.stream().filter { x -> x.type == "planned" }.collect(Collectors.toList()),
                Glide.with(this@PlannedFragment),
                onClick = {
                }
            )
            rvPlanned.adapter = adapter
            rvPlanned.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}