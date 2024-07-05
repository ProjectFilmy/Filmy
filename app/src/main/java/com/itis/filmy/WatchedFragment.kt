package com.itis.filmy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.filmy.databinding.FragmentWatchedBinding

class WatchedFragment: Fragment(R.layout.fragment_watched) {

    private var binding: FragmentWatchedBinding?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWatchedBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}