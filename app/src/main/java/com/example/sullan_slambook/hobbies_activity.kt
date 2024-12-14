package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityHobbiesBinding
import com.example.sullan_slambook.model.SlambookInfo

class hobbies_activity : AppCompatActivity() {

    private lateinit var binding: ActivityHobbiesBinding
    private val slamPageList = mutableListOf<SlambookInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHobbiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Validate inputs and navigate to My Emotion Activity
        binding.btnhobbiestoemotion.setOnClickListener {
            // Collect inputs
            val favoriteHobby = binding.edtHobby.text.toString()
            val relaxation = binding.edtRelax.text.toString()
            val indispensableHobby = binding.edtIndispensableHobby.text.toString()
            val happiestMoment = binding.edtHappiestMoment.text.toString()
            val inspiration = binding.edtInspiration.text.toString()

            // Validate all fields
            if (favoriteHobby.isBlank() || relaxation.isBlank() || indispensableHobby.isBlank() || happiestMoment.isBlank() || inspiration.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Navigate to My Emotion Activity
                val intent = Intent(this, myemotion_activity::class.java)
                startActivity(intent)
            }
            val slambookInfo = SlambookInfo(
                favoriteHobby = favoriteHobby,
                wayToRelax = relaxation,
                essentialHobby = indispensableHobby,
                happiestMoment = happiestMoment,
                dailyInspiration = inspiration,)
            slamPageList.add(slambookInfo)

            // Navigate to MySlamPagesActivity with the list of SlamPages
            val intent = Intent(this, myemotion_activity::class.java)
            intent.putParcelableArrayListExtra("slambookInfo", ArrayList(slamPageList))
            startActivity(intent)

        }



        // Back to Favorites Activity
        binding.hobbiesbtnback.setOnClickListener {
            val backIntent = Intent(this, FavoritesActivity::class.java)
            startActivity(backIntent)
            finish() // Avoid backstack issues
        }
    }
}
