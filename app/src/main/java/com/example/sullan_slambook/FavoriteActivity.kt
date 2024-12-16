package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityFavoritesBinding
import com.example.sullan_slambook.model.UserInfo

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private val slamPageList = mutableListOf<UserInfo>()
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefHelper = SharedPrefHelper(this)  // Initialize SharedPrefHelper
        loadSavedData()  // Load data when the activity is created

        // Set up the spinner with color options
        val colors = resources.getStringArray(R.array.colors_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorSpinner.adapter = adapter

        // Back button to go to ComposeActivity
        binding.favoritesbtnback.setOnClickListener {
            startActivity(Intent(this, HobbiesActivity::class.java))
            finish()
        }

        // Button to save data and move to hobbies activity
        binding.btnfavtohobbies.setOnClickListener {
            val color = binding.colorSpinner.selectedItem.toString()
            val place = binding.place.text.toString()
            val fruit = binding.fruit.text.toString()
            val food = binding.food.text.toString()
            val song = binding.song.text.toString()
            val movie = binding.movie.text.toString()
            val artist = binding.artist.text.toString()

            if (place.isBlank() || fruit.isBlank() || food.isBlank() || song.isBlank() || movie.isBlank() || artist.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create UserInfo object from input fields
            val userInfo = UserInfo(
                color = color,
                place = place,
                fruit = fruit,
                food = food,
                song = song,
                movie = movie,
                artist = artist
            )

            // Add the new user info to the list
            slamPageList.add(userInfo)

            // Save the updated list to SharedPreferences
            saveData()

            // Navigate to hobbies activity with the slamPageList
            val toHobbies = Intent(this, HobbiesActivity::class.java)
            toHobbies.putParcelableArrayListExtra("slambookInfo", ArrayList(slamPageList))
            startActivity(toHobbies)
        }
    }

    // Save the list of UserInfo objects using SharedPrefHelper
    private fun saveData() {
        sharedPrefHelper.saveUserInfoList(slamPageList)  // Save list of UserInfo objects
    //    Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
    }


    private fun loadSavedData() {
        slamPageList.clear()
        slamPageList.addAll(sharedPrefHelper.getUserInfoList())  // Load list of UserInfo objects
    }
}
