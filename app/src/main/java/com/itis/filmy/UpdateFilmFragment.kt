package com.itis.filmy

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.itis.filmy.FilmsRepository.films
import com.itis.filmy.databinding.FragmentUpdateBinding

class UpdateFilmFragment : Fragment(R.layout.fragment_update) {

    private var binding: FragmentUpdateBinding? = null
    private lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)

        glide = Glide.with(this)

        val filmId = arguments?.getInt("FILM_ID")
        val film = FilmsRepository.films.find {it.id == filmId} ?: return

        binding?.run {
            inputEditName2.setText(film.name)
            inputEditGenre2.setText(film.genre)
            inputEditDate2.setText(film.date)
            rating2.rating = film.rating
            inputEditComment2.setText(film.comment)
            when(film.type){
                "planned" -> {inputComment2.visibility = View.GONE; rating2.visibility = View.GONE }
                "watched" -> {}
            }


            imageViewBack2.setOnClickListener {
                findNavController().popBackStack()
            }
            button3.setOnClickListener {
                if (filmId != null) {
                    FilmsRepository.updateFilm(Film(filmId, spinner2.selectedItem.toString() ,inputEditName2.text.toString(), inputEditGenre2.text.toString(), inputEditDate2.text.toString(),inputEditComment2.text.toString(), rating2.rating, inputEditUrl.text.toString()))
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}