package com.itis.filmy

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.itis.filmy.databinding.ItemFilmBinding

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val glide: RequestManager,
    private val onClick: (Int) -> Unit
): ViewHolder(binding.root) {
    fun onBind(film: Film){
        binding.run {
            tvFilm.text = film.name

            glide
                .load(film.url)
                .into(ivFilm)

            root.setOnClickListener {
                onClick.invoke(film.id)
            }
        }
    }
}