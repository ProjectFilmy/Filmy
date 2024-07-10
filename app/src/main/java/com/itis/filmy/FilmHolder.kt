package com.itis.filmy

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.itis.filmy.databinding.ItemFilmBinding

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val onClick: (Int) -> Unit
): ViewHolder(binding.root) {
    fun onBind(film: Film){

        binding.run {
            tvFilm.text = film.name

            try {
                Glide.with(ivFilm)
                    .load(Uri.parse(film.uri))
                    .into(ivFilm)
            }
            catch (e: Exception) {
                e.printStackTrace()
            }

            root.setOnClickListener {
                onClick.invoke(film.id)
            }
        }
    }
}