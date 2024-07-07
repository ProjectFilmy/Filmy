package com.itis.filmy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itis.filmy.databinding.FragmentPlannedBinding

class PlannedFragment : Fragment(R.layout.fragment_planned) {
    private var binding: FragmentPlannedBinding?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlannedBinding.bind(view)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}