package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityHobbiesBinding
import com.example.sullan_slambook.model.UserInfo

class HobbiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHobbiesBinding
    private val slamPageList = mutableListOf<UserInfo>()
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHobbiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefHelper = SharedPrefHelper(this)  // Initialize SharedPrefHelper
        loadSavedData()  // Load data when the activity is created

        // Handle "Next to My Emotion" button click
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
                return@setOnClickListener
            }

            // Create UserInfo object for hobbies
            val userInfo = UserInfo(
                favoriteHobby = favoriteHobby,
                wayToRelax = relaxation,
                essentialHobby = indispensableHobby,
                happiestMoment = happiestMoment,
                dailyInspiration = inspiration
            )
            slamPageList.add(userInfo)

            // Save the updated list to SharedPreferences
            saveData()

            // Navigate to My Emotion Activity
            val intent = Intent(this, MyEmotionActivity::class.java)
            intent.putParcelableArrayListExtra("slambookInfo", ArrayList(slamPageList))
            startActivity(intent)
        }

        // Back to Favorites Activity
        binding.hobbiesbtnback.setOnClickListener {
            val backIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(backIntent)
            finish() // Avoid backstack issues
        }
    }

    // Save the list of UserInfo objects using SharedPrefHelper
    private fun saveData() {
        sharedPrefHelper.saveUserInfoList(slamPageList)  // Save list of UserInfo objects
      //  Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
    }

    // Load the list of UserInfo objects using SharedPrefHelper
    private fun loadSavedData() {
        slamPageList.clear()
        slamPageList.addAll(sharedPrefHelper.getUserInfoList())  // Load list of UserInfo objects
    }
}
