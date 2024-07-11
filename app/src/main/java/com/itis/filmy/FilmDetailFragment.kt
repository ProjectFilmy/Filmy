package com.itis.filmy

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.itis.filmy.FilmsRepository.films
import com.itis.filmy.databinding.FragmentFilmDetailBinding

class FilmDetailFragment : Fragment(R.layout.fragment_film_detail) {

    private var binding: FragmentFilmDetailBinding? = null
    private lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFilmDetailBinding.bind(view)

        glide = Glide.with(this)

        val filmId = arguments?.getInt("FILM_ID")
        val film = films.find {it.id == filmId} ?: return

        binding?.run {
            try{
                Glide.with(imageView)
                    .load(Uri.parse(film.uri))
                    .into(imageView)
            }
            catch(e:Exception){
                e.printStackTrace()
            }

            Title.text = film.name
            genre.text = "Жанр фильма: " + film.genre
            dateOfRelease.text = "Дата выхода: " + film.date
            rating.rating = film.rating
            comment.text = "Комментарий: " + film.comment
            when(film.type){
                "Planned" -> {comment.visibility = View.GONE; rating.visibility = View.GONE }
                "Watched" -> {}
            }

            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }

            deleteButton.setOnClickListener {
                if (filmId != null) {
                    FilmsRepository.deleteFilm(filmId)
                    findNavController().popBackStack()
                }
            }

            updateButton.setOnClickListener{
                findNavController().navigate(
                    R.id.action_filmDetailFragment_to_updateFilmFragment,
                    bundleOf("FILM_ID" to filmId)

                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}