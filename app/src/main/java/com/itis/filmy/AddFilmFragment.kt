package com.itis.filmy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.itis.filmy.FilmsRepository.films
import com.itis.filmy.databinding.FragmentAddFilmBinding

class AddFilmFragment: Fragment(R.layout.fragment_add_film) {
    private var binding: FragmentAddFilmBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddFilmBinding.bind(view)
        addingFilm()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    fun addingFilm(){
        binding?.run {
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    println(selectedItem)
                    when(selectedItem){
                        "Planned" -> {rating.visibility = View.GONE
                            inputComment.visibility = View.GONE
                            inputEditComment.visibility = View.GONE}
                        "Watched" -> {rating.visibility = View.VISIBLE
                            inputComment.visibility = View.VISIBLE
                            inputEditComment.visibility = View.VISIBLE}
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            button.setOnClickListener {
                FilmsRepository.insertFilm(Film(FilmsRepository.id++, spinner.selectedItem.toString() ,inputEditName.text.toString(), inputEditGenre.text.toString(), inputEditDate.text.toString(),inputEditComment.text.toString(), rating.rating, inputEditUrl.text.toString()))
                Log.d("BEBRABEBRABEBRA",films.toString())
            }
        }
    }
}
