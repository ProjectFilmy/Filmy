package com.itis.filmy

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.itis.filmy.FilmsRepository.films
import com.itis.filmy.databinding.FragmentAddFilmBinding

class AddFilmFragment: Fragment(R.layout.fragment_add_film) {

    private var binding: FragmentAddFilmBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddFilmBinding.bind(view)
        addingFilm()
    }

    fun addingFilm(){
        binding?.run {
            var galleryUri: Uri? = null
            val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
                if (it != null) {
                    galleryUri = it
                }
            }
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
            tvAddImage.setOnClickListener {
                galleryLauncher.launch("image/*")
            }
            button.setOnClickListener {
                if (validateForm()){
                    films.add(
                        Film
                            (films.size,
                            spinner.selectedItem.toString(),
                            inputEditName.text.toString(),
                            inputEditGenre.text.toString(),
                            inputEditDate.text.toString(),
                            if (spinner.selectedItem.toString() == "Planned") "null" else inputEditComment.text.toString(),
                            rating.rating,
                            galleryUri.toString())
                    )
                    Toast.makeText(context, "Movie added", Toast.LENGTH_SHORT).show()
                    clear()

                }else{
                    Toast.makeText(context, "You need to fill in all the fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateForm(): Boolean{
        var valid = true
        binding?.run {
            if (inputEditName.text.toString().isEmpty() ||
                inputEditGenre.text.toString().isEmpty() ||
                inputEditDate.text.toString().isEmpty() ||
                (spinner.selectedItem.toString() == "Watched" && inputEditComment.text.toString().isEmpty())){
                valid = false
            }

        }
        return valid
    }

    private fun clear(){
        binding?.run {
            inputEditName.text?.clear()
            inputEditGenre.text?.clear()
            inputEditDate.text?.clear()
            inputEditComment.text?.clear()
            rating.rating = 0f
            spinner.setSelection(0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

