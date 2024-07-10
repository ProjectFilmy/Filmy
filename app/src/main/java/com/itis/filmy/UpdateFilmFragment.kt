package com.itis.filmy

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
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
        val film = films.find {it.id == filmId} ?: return

        binding?.run {
            inputEditName2.setText(film.name)
            inputEditGenre2.setText(film.genre)
            inputEditDate2.setText(film.date)
            rating2.rating = film.rating

            if (film.type == "Watched") inputEditComment2.setText(film.comment) else inputEditComment2.setText("")
            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    println(selectedItem)
                    when(selectedItem){
                        "Planned" -> {rating2.visibility = View.GONE
                            inputComment2.visibility = View.GONE
                            inputEditComment2.visibility = View.GONE}
                        "Watched" -> {rating2.visibility = View.VISIBLE
                            inputComment2.visibility = View.VISIBLE
                            inputEditComment2.visibility = View.VISIBLE}
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

            var galleryUri: Uri? = null
            val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
                if (it != null) {
                    galleryUri = it
                }
            }

            tvAddImage.setOnClickListener {
                galleryLauncher.launch("image/*")
            }

            imageViewBack2.setOnClickListener {
                findNavController().popBackStack()
            }

            button3.setOnClickListener {
                if (filmId != null) {
                    FilmsRepository.updateFilm(Film(filmId,
                        spinner2.selectedItem.toString(),
                        inputEditName2.text.toString(),
                        inputEditGenre2.text.toString(),
                        inputEditDate2.text.toString(),
                        inputEditComment2.text.toString(),
                        rating2.rating,
                        if (galleryUri == null) film.uri else galleryUri.toString()))
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