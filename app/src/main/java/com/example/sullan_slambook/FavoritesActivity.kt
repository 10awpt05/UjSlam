package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityFavoritesBinding
import com.example.sullan_slambook.model.SlambookInfo

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private val slamPageList = mutableListOf<SlambookInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up color spinner with predefined colors
        val colors = resources.getStringArray(R.array.colors_array) // Array from strings.xml
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorSpinner.adapter = adapter

        // Back button functionality
        binding.favoritesbtnback.setOnClickListener {
            val compose = Intent(this, ComposeActivity::class.java)
            startActivity(compose)
            finish() // Close the activity and return to the previous screen
        }

        // Handle "Next to Hobbies" button click
        binding.btnfavtohobbies.setOnClickListener {
            // Gather data from the input fields and spinner
            val color = binding.colorSpinner.selectedItem.toString()
            val place = binding.place.text.toString()
            val fruit = binding.fruit.text.toString()
            val food = binding.food.text.toString()
            val song = binding.song.text.toString()
            val movie = binding.movie.text.toString()
            val artist = binding.artist.text.toString()

            // Simple validation to check if any field is empty
            if (place.isBlank() || fruit.isBlank() || food.isBlank() || song.isBlank() || movie.isBlank() || artist.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Add SlambookInfo to the list
            val slambookInfo = SlambookInfo(
                color = color,
                place = place,
                fruit = fruit,
                food = food,
                song = song,
                movie = movie,
                artist = artist,
            )
            slamPageList.add(slambookInfo)

            // If all fields are filled, proceed to Hobbies Activity
            val toHobbies = Intent(this, hobbies_activity::class.java)
            toHobbies.putParcelableArrayListExtra("slambookInfo", ArrayList(slamPageList))
            startActivity(toHobbies)
        }
    }
}
