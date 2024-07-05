package com.itis.filmy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.filmy.databinding.FragmentPlannedBinding

class PlannedFragment: Fragment(R.layout.fragment_planned) {

    private var binding: FragmentPlannedBinding?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlannedBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}