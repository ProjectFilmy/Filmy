package com.itis.filmy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.filmy.databinding.FragmentAddFilmBinding

class AddFilmFragment: Fragment(R.layout.fragment_add_film) {
    private var binding: FragmentAddFilmBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddFilmBinding.bind(view)
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}