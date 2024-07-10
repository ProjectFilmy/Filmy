package com.itis.filmy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.itis.filmy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null

    private var controller: NavController? = null

    private var pref: SharedPreferences ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        pref = getSharedPreferences("FILMS", Context.MODE_PRIVATE)

        getDataFromPref()

        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
            .navController

        controller?.let { navController ->
            binding?.bottomNavigation?.setupWithNavController(navController)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
        binding = null
    }
    override fun onPause(){
        super.onPause()
        saveData()
    }


    override fun onStop(){
        super.onStop()
        saveData()
    }

    private fun getDataFromPref(){

        val size = pref?.getInt("size", 0)

        if (size != null) {
            repeat(size){
                pref?.getString("$it", "")
                    ?.let {film -> FilmsRepository.films.add(stringToFilm(film)) }!!
            }
        }
    }

    private fun saveData(){
        val count = FilmsRepository.films.size
        val films = FilmsRepository.films
        val editor = pref?.edit()

        repeat(count){
            editor?.putString("$it", films[it].toString())
            editor?.apply()
        }

        editor?.putInt("size", count)
        editor?.apply()

    }

    private fun stringToFilm(str: String): Film {
        val array = str.split(";")
        return Film(
            Integer.parseInt(array[0]),
            array[1],
            array[2],
            array[3],
            array[4],
            array[5],
            array[6].toFloat(),
            array[7]
        )
    }
}

